/*-----------------------------------------------------------------------------------------------------------------------
	Homework-006. sorunun bir çözümü
	
	Not: Simulasyona ilişkin sınıfta her oyun yeni bir Craps nesnesi olarak ele alınmıştır. Bu durumda Craps sınıfı
	gelistirilip başka bir takım verileri de tutacak şekilde tasarlandığında gerçekten ayrı nesnelerin oluşturulması
	gerekecektir. Bu düşünce ile simülasyon sınıfı yazılmıştır
-----------------------------------------------------------------------------------------------------------------------*/
package csd;

class App {
    public static void main(String[] args)
    {
        CrapsSimulationApp.run();
    }
}

class CrapsSimulationApp {
    public static void run()
    {
        java.util.Scanner kb = new java.util.Scanner(System.in);

        for (;;) {
            System.out.print("Kaç kez oynatmak istersiniz?");
            int count = Integer.parseInt(kb.nextLine());

            if (count <= 0)
                break;

            System.out.println("-------------------------------------------------");
            CrapsSimulation simulation = new CrapsSimulation();

            simulation.run(count);
            System.out.printf("Kazanma olasılığı: %f%n", simulation.p);
            System.out.println("-------------------------------------------------");

        }
    }
}

class CrapsSimulation {
    public double p;

    public void run(int count)
    {
        int winCount = 0;

        for (int i = 0; i < count; ++i) {
            Craps craps = new Craps();

            craps.play();

            if (craps.win)
                ++winCount;
        }

        p = (double)winCount / count;
    }
}

class Craps {
    public boolean win;

    public static int roll(java.util.Random r)
    {
        return r.nextInt(1, 7) + r.nextInt(1, 7);
    }

    public void rollForIndeterminate(java.util.Random r, int result)
    {
        int total;

        while ((total = roll(r)) != result && total != 7)
            ;

        win = total == result;
    }

    public void play()
    {
        java.util.Random r = new java.util.Random();

        int total = roll(r);

        switch (total) {
            case 7, 11 -> win = true;
            case 2, 3, 12 -> win = false;
            default -> rollForIndeterminate(r, total);
        }
    }
}
