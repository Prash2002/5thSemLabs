# Questions:

### 1. Three nodes point – to – point network with duplex links between them. Set the queue size, vary the bandwidth and find the number of packets dropped.

cp examples/traffic-control/traffic-control.cc scratch/ques1.cc

1. remove functions and NS-LOG
2. set socketType to UDP and remove command args stuff
3. Create 3 nodes
4. Create 2 devices for nodes(0,1) and (1,2) and pass the nodes in parameter using nodes.Get(0)
5. Remove TrafficControlHelper till IPv4Adress
6. Create 2 interfaces for IPs: 10.1.1.0 and 10.1.2.0 and add routingtables Ipv4GlobalRoutingHelper::PopulateRoutingTables ();

   - sinkapp set node to 2

7. Remove payload and remove packetSize attribute from onoff
8. Remove 0xb8 and replace inetSocket interface with interface12 and get 1
   - onoff install - get 0
9. Remove everything below Simulator destroy
10. Remove unnecessary cout statements and add

```
    for (auto i = stats.begin(); i != stats.end(); i++)
    {
        std::cout << "tx packets: " <<i->second.txPackets << "\n";
        std::cout << "Lost Packets: " << i->second.lostPackets << "\n";
    }
```

11. To run ./waf --run scratch/filenameWithoutExtention

## 2. Simulate a four node point-to-point network, and connect the links as follows: n0-n2, n1-n2 and n2-n3. Apply TCP agent between n0-n3 and UDP agent between n1-n3. Apply relevant applications over TCP and UDP agents by changing the parameters and determine the number of packets sent by TCP/UDP

## 3. Simulate simple Extended Service Set with transmitting nodes in wireless LAN and determine the performance with respect to transmission of packets.

cp examples/tutorial/third.cc scratch/ques3.c

0. #include "ns3/flow-monitor-module.h"
1. Remove cmd, tracing and verbose stuff and set std::string socketType = "ns3::UdpSocketFactory"; and double simulationTime = 10;
2. change nWifi 18 to nWifi > 250 || nCsma > 250
3. Comment UDP echoServer thing
4. Add flow from examples/traffic-control/traffic-control.cc
5. in flow sinkApp add csmaNode and get nCsma
6. in remote address add csmaInterfaces and get nCsma, remove 0xb8
7. in apps.App install _wifiStaNodes.Get (nWifi - 1)_
8. Remove if tracing part
9. add output stuff:

```
  FlowMonitorHelper flowmon;
  Ptr<FlowMonitor> monitor = flowmon.InstallAll();
  Simulator::Run();

  monitor->CheckForLostPackets();
  Ptr<Ipv4FlowClassifier> classifier = DynamicCast<Ipv4FlowClassifier>(flowmon.GetClassifier());
  std::map<FlowId, FlowMonitor::FlowStats> stats = monitor->GetFlowStats();
  for (auto iter = stats.begin(); iter != stats.end(); ++iter)
  {
    Ipv4FlowClassifier::FiveTuple t = classifier->FindFlow(iter->first);
    NS_LOG_UNCOND("Flow ID: " << iter->first << " Src Addr " << t.sourceAddress << " Dst Addr " << t.destinationAddress);
    NS_LOG_UNCOND("Tx Packets = " << iter->second.txPackets);
    std::cout << "Rx Packets = " << iter->second.rxPackets << std::endl;
    std::cout << "Lost Packets = " << iter->second.lostPackets << std::endl;
    std::cout << "Throughput = " << iter->second.rxBytes * 8.0 / (iter->second.timeLastRxPacket.GetSeconds() - iter->second.timeFirstTxPacket.GetSeconds()) / 1000000 << " Kbps" << std::endl;
  }
 }
```
