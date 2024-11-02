

import java.text.DecimalFormat;
import java.util.*;

public class assignment2 {

    public static void displayTable(String[] columnNames, double[][] data) {

        for (String name : columnNames) {
            System.out.printf("%-12s", name);
        }
        System.out.println();

        for (int i = 0; i < columnNames.length; i++) {
            System.out.print("-----------");
        }
        System.out.println();

        // Print the data
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.printf("%-12.2f", data[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.##");

        // System.out.println("");
        System.out.println("Enter Number of Customers: ");

        double n = in.nextDouble();

        System.out.println("Enter random arrival intervals (1-9)");
        double random_arrive = in.nextDouble();

        System.out.println("Enter Able Random service time (1-9)");
        double random_service1 = in.nextDouble();
        System.out.println("Enter Baker Random service time (1-9)");
        double random_service2 = in.nextDouble();

        int[] RIAT = new int[(int) n];
        int[] RST = new int[(int) n];
        String[] columns = { "Mins.", "Prob", "Cum. Prob", "Min range", "Max range" };
        String[] Final_columns = { "Customer", "IAT (mins)", "Arrival", "A Serv time", "Begin",
                "End", "B Serv time","Begin", "End", "Wait A",
                "In Sys", "A Idle","B idle" ,"wait B"};

        double[][] IAT_table = new double[(int) random_arrive][5];
        double[][] ST_table1 = new double[(int) random_service1][5];
        double[][] ST_table2 = new double[(int) random_service2][5];
        double[][] Final_table = new double[(int) n][14];

        for (int i = 0; i < n; i++) {
            RIAT[i] = (int) (Math.random() * 100 + 1);
        }

        for (int i = 0; i < n; i++) {
            RST[i] = (int) (Math.random() * 100 + 1);
        }

        // for (int i = 0; i < n; i++) {
        // System.out.println(RST[i]);
        // }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < random_arrive; j++) {
                if (i == 0) {
                    IAT_table[j][i] = j + 1;
                }

                if (i == 1) {
                    IAT_table[j][i] = 1.0 / random_arrive;
                }

                if (i == 2) {
                    IAT_table[j][i] = Double.parseDouble(df.format((1.0 / random_arrive) * (j + 1)));
                }

                if (i == 3) {
                    IAT_table[j][i] = (int) ((1.0 / random_arrive) * (j) * 100) + 1;
                }

                if (i == 4) {
                    IAT_table[j][i] = (int) ((1.0 / random_arrive) * (j + 1) * 100);
                }

            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < random_service1; j++) {
                if (i == 0) {
                    ST_table1[j][i] = j + 1;
                }

                if (i == 1) {
                    ST_table1[j][i] = Double.parseDouble(df.format(1.0 / random_service1));
                }

                if (i == 2) {
                    ST_table1[j][i] = Double.parseDouble(df.format((1.0 / random_service1) * (j + 1)));
                }

                if (i == 3) {
                    ST_table1[j][i] = (int) ((1.0 / random_service1) * (j) * 100) + Math.min(1, j);
                }

                if (i == 4) {
                    ST_table1[j][i] = (int) ((1.0 / random_service1) * (j + 1) * 100);
                }

            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < random_service2; j++) {
                if (i == 0) {
                    ST_table2[j][i] = j + 1;
                }

                if (i == 1) {
                    ST_table2[j][i] = Double.parseDouble(df.format(1.0 / random_service2));
                }

                if (i == 2) {
                    ST_table2[j][i] = Double.parseDouble(df.format((1.0 / random_service2) * (j + 1)));
                }

                if (i == 3) {
                    ST_table2[j][i] = (int) ((1.0 / random_service2) * (j) * 100) + Math.min(1, j);
                }

                if (i == 4) {
                    ST_table2[j][i] = (int) ((1.0 / random_service2) * (j + 1) * 100);
                }

            }
        }

        int sumWaiting = 0;
        int sumST1 = 0;
        int sumST2 = 0;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    Final_table[j][i] = j + 1;
                }

                if (i == 1) {
                    for (int l = 0; l < random_arrive; l++) {
                        if (RIAT[j] >= IAT_table[l][3] && RIAT[j] <= IAT_table[l][4] && j != 0) {
                            Final_table[j][i] = l + 1;
                        }
                    }

                }

                if (i == 2) {
                    if (j != 0) {
                        Final_table[j][i] = Final_table[j - 1][i] + Final_table[j][i - 1];
                    }
                }

                if (i == 3) {
                    for (int l = 0; l < random_service1; l++) {
                        if (RST[j] >= ST_table1[l][3] && RST[j] <= ST_table1[l][4]) {
                            Final_table[j][i] = l + 1;
                        }
                    }
                    sumST1 += Final_table[j][i];
                }

                
                if (i == 6) {
                    for (int l = 0; l < random_service2; l++) {
                        if (RST[j] >= ST_table2[l][3] && RST[j] <= ST_table2[l][4]) {
                            Final_table[j][i] = l + 1;
                        }
                    }
                    sumST2 += Final_table[j][i];
                }
                
                if (i == 4) {
                    if (j != 0 && Final_table[j][2] < Final_table[j - 1][5]) {
                        if (Final_table[j][2] > Final_table[j - 1][8]) {
                            Final_table[j][i+3] = Math.max(Final_table[j][2], Final_table[j - 1][8]);
                        }
                        Final_table[j][i + 4] = Final_table[j][3] + Final_table[j][i + 3];
                        Final_table[j][i] = 0;
                        Final_table[j][i + 1] = Final_table[j-1][i+1];
                    }
                    else if(j !=0) {
                        Final_table[j][i] = Math.max(Final_table[j][2], Final_table[j - 1][5]);
                        Final_table[j][i + 1] = Final_table[j][3] + Final_table[j][4];
                    }
                }
                // if (i == 7) {
                //     Final_table[j][i] = 0;

                // }

                // if (i == 8) {
                //     Final_table[j][i] = 0;
                // }
                if (i == 9) {
                    Final_table[j][i] = Final_table[j][4] - Final_table[j][2];
                    sumWaiting += Final_table[j][i];

                }
                if (i == 10) {
                    Final_table[j][i] = Final_table[j][5] - Final_table[j][2];
                }
                if (i == 11) {
                    if (j != 0) {

                        Final_table[j][i] = Math.max(0, (Final_table[j][2] - Final_table[j - 1][5]));
                    }
                }

            }
        }

        //idle  and wait calculator
        Final_table[0][11] = Final_table[0][4];
        for(int i = 1;i<n;i++){
            // begin A = 4, begin B= 7
            // end A =5, end B= 8
            // idle A =11, idle B= 12

            //idle server A = begin[i]- end[i-1]
            Final_table[i][11] = Math.max(Final_table[i][4] - Final_table[i-1][5], 0);

            //idle server B
            Final_table[i][12] = Math.max(Final_table[i][7] - Final_table[i-1][8], 0);
            

            //wait = begin - arrive
            //wait a =9 wait b = 13
            //arriavle=2
            Final_table[i][9]=Math.max(Final_table[i][4]-Final_table[i][2],0);//wait A
            Final_table[i][13]=Math.max(Final_table[i][7]-Final_table[i][2],0);// wait B

            
         }

        System.out.println();
        System.out.println("IAT table:");
        displayTable(columns, IAT_table);
        System.out.println();
        System.out.println();
        System.out.println("ST table 1:");
        displayTable(columns, ST_table1);
        System.out.println("ST table 2:");
        displayTable(columns, ST_table2);
        System.out.println();
        System.out.println();
        System.out.println("Final table:");
        displayTable(Final_columns, Final_table);
        System.out.println();
    
        System.out.println("Average Service Time for Able: " + (sumST1 / n));
        System.out.println("Average Service Time for Baker: " + (sumST2 / n));

    }
}