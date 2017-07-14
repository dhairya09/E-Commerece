import java.util.Scanner;

/**
 * Created by Dhairya Pujara on 01-09-2016.
 */
public class Win
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println("N:"+N);
        String[] a= new String[N];
        int b[] = new int[N];
        int cnt = 0;
        int max = 0;
        //if(N>=8 && N<=10)
        //{
        for(int i=0;i<N;i++)
        {
            cnt = 0;
            a[i] = sc.nextLine();
            String x[] = a[i].split("");
                 for(int j=0;j<x.length;j++)
                {
                    System.out.println("x["+j+"]:"+x[j]);

                }
            for(int k=0;k<x.length;k++)
            {
                if(x[k].equals("W")){
                    cnt++;
                }
            }
            b[i]  = cnt;
            System.out.println("b["+i+"]:"+b[i]);


        }
        max =  b[0];
        for(int i=0;i<N;i++)
        {
            if(b[i] > max)
            {
                max = b[i];
            }
        }
        System.out.println("max:"+max);
    }
}
