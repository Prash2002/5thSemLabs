# Definitions

- Node - a host
- Channel - the media over which data flows in these networks are called channels
- Peripheral card - to connect a computer to a network, you had to buy a specific kind of network cable and a hardware device called (in PC terminology) a peripheral card
- NIC - the peripheral card implemented some networking function, they were called Network Interface Cards, or NICs
- a piece of peripheral hardware is classified as a _device_ . Devices are controlled using _device drivers_, and network devices (NICs) are controlled using network device drivers collectively known as _net devices_. In Unix and Linux you refer to these net devices by names such as eth0

# Topology Helpers

### NodeContainer

```cpp
    NodeContainer nodes;
    nodes.Create(2);
```

### PointToPointHelper

- connect our nodes together into a network. The simplest form of network we support is a single point-to-point link between two nodes

```
    PointToPointHelper pointToPoint;
    pointToPoint.SetDeviceAttribute ("DataRate", StringValue ("5Mbps"));
    pointToPoint.SetChannelAttribute ("Delay", StringValue ("2ms"));
```

### NetDeviceContainer

```
    NetDeviceContainer devices;
    devices = pointToPoint.Install (nodes);
```

### InternetStackHelper

- it will install an Internet Stack (TCP, UDP, IP, etc.) on each of the nodes in the node container.

```
    InternetStackHelper stack;
    stack.Install (nodes);
```

### Ipv4AddressHelper

- declare an address helper object and tell it that it should begin allocating IP addresses from the network 10.1.1.0 using the mask 255.255.255.0 to define the allocatable bits. By default the addresses allocated will start at one and increase monotonically

```
    Ipv4AddressHelper address;
    address.SetBase ("10.1.1.0", "255.255.255.0");
```

### UdpEchoServerHelper

```
    UdpEchoServerHelper echoServer (9);

    ApplicationContainer serverApps = echoServer.Install (nodes.Get (1));
    serverApps.Start (Seconds (1.0));
    serverApps.Stop (Seconds (10.0));
```

### Simulator

```
        Simulator::Run ();
        ...
        ...
        Simulator::Destroy ();
        return 0;
    }
```

# Building a Bus Network Topology

## CSMA/ CD

---

- The basic idea behind CSMA/CA is that the station should be able to receive while transmitting to detect a collision from different stations.
- In wired networks, if a collision has occurred then the energy of received signal almost doubles and the station can sense the possibility of collision.
- In case of wireless networks, most of the energy is used for transmission and the energy of received signal increases by only 5-10% if a collision occurs. It can’t be used by the station to sense collision.
- Therefore CSMA/CA has been specially designed for wireless networks

## ns3:

```
    NodeContainer p2pNodes;
    p2pNodes.Create (2);

    NodeContainer csmaNodes;
    csmaNodes.Add (p2pNodes.Get (1));
    csmaNodes.Create (nCsma);
```

### CSMAHelper

- The CsmaHelper works just like a PointToPointHelper, but it creates and connects CSMA devices and channels. In the case of a CSMA device and channel pair, notice that the data rate is specified by a channel Attribute instead of a device Attribute

```
    CsmaHelper csma;
    csma.SetChannelAttribute ("DataRate", StringValue ("100Mbps"));
    csma.SetChannelAttribute ("Delay", TimeValue (NanoSeconds (6560)));

    NetDeviceContainer csmaDevices;
    csmaDevices = csma.Install (csmaNodes);
```

### Routing Table

```
    Ipv4GlobalRoutingHelper::PopulateRoutingTables ();
```

## Building a Wireless Network Topology

- create the nodes that will be part of the Wi-Fi network. We are going to create a number of “station” nodes and we are going to use the “leftmost” node of the point-to-point link as the node for the access point.

```
    NodeContainer wifiStaNodes;
    wifiStaNodes.Create (nWifi);
    NodeContainer wifiApNode = p2pNodes.Get (0);
```

- constructs the wifi devices and the interconnection channel between these wifi nodes

```
    YansWifiChannelHelper channel = YansWifiChannelHelper::Default ();
    YansWifiPhyHelper phy = YansWifiPhyHelper::Default ();
    phy.SetChannel (channel.Create ());
```

- WifiMacHelper object is used to set MAC parameters.

```
    WifiHelper wifi;
    wifi.SetRemoteStationManager ("ns3::AarfWifiManager");

    WifiMacHelper mac;

    Ssid ssid = Ssid ("ns-3-ssid");
    mac.SetType ("ns3::StaWifiMac",
    "Ssid", SsidValue (ssid),
    "ActiveProbing", BooleanValue (false));
```

- create the Wi-Fi devices of these stations

```
    NetDeviceContainer staDevices;
    staDevices = wifi.Install (phy, mac, wifiStaNodes);
    mac.SetType ("ns3::ApWifiMac", "Ssid", SsidValue (ssid));
    NetDeviceContainer apDevices;
    apDevices = wifi.Install (phy, mac, wifiApNode);
```

- We want the STA nodes to be mobile, wandering around inside a bounding box, and we want to make the AP node stationary. We use the MobilityHelper to make this easy for us. First, we instantiate a MobilityHelper object and set some Attributes controlling the “position allocator” functionality.

```
    MobilityHelper mobility;

    mobility.SetPositionAllocator ("ns3::GridPositionAllocator",
    "MinX", DoubleValue (0.0),
    "MinY", DoubleValue (0.0),
    "DeltaX", DoubleValue (5.0),
    "DeltaY", DoubleValue (10.0),
    "GridWidth", UintegerValue (3),
    "LayoutType", StringValue ("RowFirst"));

    mobility.SetMobilityModel ("ns3::RandomWalk2dMobilityModel", "Bounds", RectangleValue (Rectangle (-50, 50, -50, 50)));

    mobility.Install (wifiStaNodes);

```

- We want the access point to remain in a fixed position during the simulation. We accomplish this by setting the mobility model for this node to be the ns3::ConstantPositionMobilityModel:

```
    mobility.SetMobilityModel ("ns3::ConstantPositionMobilityModel");
    mobility.Install (wifiApNode);
```

- assign IP addresses to our device interfaces

```
    Ipv4AddressHelper address;

    address.SetBase ("10.1.1.0", "255.255.255.0");
    Ipv4InterfaceContainer p2pInterfaces;
    p2pInterfaces = address.Assign (p2pDevices);

    address.SetBase ("10.1.2.0", "255.255.255.0");
    Ipv4InterfaceContainer csmaInterfaces;
    csmaInterfaces = address.Assign (csmaDevices);

    address.SetBase ("10.1.3.0", "255.255.255.0");
    address.Assign (staDevices);
    address.Assign (apDevices);
```
