package Test;

import java.io.File;

import laboratoire3.MotsCaches;

public class Test {

    public static void main(String[] args) {
        MotsCaches mc = new MotsCaches();
        File grilleFile = new File("Test/grid_demo2.txt");
        File dictFile = new File("Test/dict_demo2.txt");
        long start = System.currentTimeMillis();
        String[] results = mc.Resoudre(grilleFile.getAbsolutePath(), dictFile.getAbsolutePath());
        long end = System.currentTimeMillis();
        System.out.println("Temps d'exécution : " + (end - start) + " ms");
        ImprimerResultats(results);
    }

    public static void ImprimerResultats(String[] resultats){
        System.out.println("Mots trouvés (" + resultats.length + ") :");
        for (int i = 0; i < resultats.length; i++) {
            System.out.println(resultats[i]);
        }
    }

}
