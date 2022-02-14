package Test;

import java.io.File;

import laboratoire3.MotsCaches;

public class Test {

    public static void main(String[] args) {
        MotsCaches mc = new MotsCaches();
        File grilleFile = new File("Test/grid_demo.txt");
        File dictFile = new File("Test/dict_demo.txt");
        long start = System.currentTimeMillis();
        mc.Resoudre(grilleFile.getAbsolutePath(), dictFile.getAbsolutePath());
        long end = System.currentTimeMillis();
        System.out.println("Temps d'exécution : " + (end - start) + " ms");
    }
    
}
