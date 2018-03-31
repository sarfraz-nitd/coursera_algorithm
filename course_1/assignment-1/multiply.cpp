
#include<bits/stdc++.h>
using namespace std;

string multiply(string num1, string num2)
{
    int n1 = num1.size();
    int n2 = num2.size();
    if (n1 == 0 || n2 == 0)
       return "0";

    vector<int> result(n1 + n2, 0);

    int i_n1 = 0;
    int i_n2 = 0;

    for (int i=n1-1; i>=0; i--)
    {
        int carry = 0;
        int n1 = num1[i] - '0';

        i_n2 = 0;

        for (int j=n2-1; j>=0; j--)
        {

            int n2 = num2[j] - '0';


            int sum = n1*n2 + result[i_n1 + i_n2] + carry;

            // Carry for next iteration
            carry = sum/10;

            // Store result
            result[i_n1 + i_n2] = sum % 10;

            i_n2++;
        }

        // store carry in next cell
        if (carry > 0)
            result[i_n1 + i_n2] += carry;

        i_n1++;
    }


    int i = result.size() - 1;
    while (i>=0 && result[i] == 0)
       i--;

    if (i == -1)
       return "0";

    // generate the result string
    string s = "";
    while (i >= 0)
        s += std::to_string(result[i--]);

    return s;
}


int main()
{
    string str1 = "3141592653589793238462643383279502884197169399375105820974944592";
    string str2 = "2718281828459045235360287471352662497757247093699959574966967627";
    cout << multiply(str1, str2);
    return 0;
}
