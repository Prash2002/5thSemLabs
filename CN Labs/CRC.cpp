#include <bits/stdc++.h>

using namespace std;

bool crc(string op, string poly)
{
    int j = 0;
    cout << op.length() << " " << poly.length() << endl;
    cout << int(op.length() - poly.length()) << endl;
    for (int i = 0; i < int(op.length() - poly.length()); i++)
    {
        if (op[i] == '1')
        {
            for (j = 0; j < poly.length(); j++)
            {
                //XOR:
                if (poly[j] == op[i + j])
                {
                    op[i + j] = '0';
                }
                else
                {
                    op[i + j] = '1';
                }
            }
        }
    }
    cout << "The crc: " << op;
    for (char c : op)
    {
        if (c == '1')
        {
            return false;
        }
    }
    return true;
}

int main()
{
    string poly = "10001000000100001";
    string ip;
    cout << "Enter the message to be transmitted: ";
    cin >> ip;

    string op = "";
    op.append(ip);
    int n = poly.length() - 1;
    while (n--)
    {
        op.append("0");
    }
    cout << op << endl;

    crc(op, poly);

    cout << "Enter the message received: ";
    cin >> ip;
    bool right = crc(ip, poly);
    if (right)
    {
        cout << "No errors detected! ";
    }
    else
    {
        cout << "Error transmitting the message";
    }
    return 0;
}