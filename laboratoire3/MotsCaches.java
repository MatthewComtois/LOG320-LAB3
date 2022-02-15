package laboratoire3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MotsCaches {
    int grosseurGrille;
    Character[][] grille;
    ArrayList<String> motsATrouver = new ArrayList<String>();
    ArrayList<String> motsTrouves = new ArrayList<String>();

    public String[] Resoudre(String nomFichierGrille, String nomFichierDict) {
        try {
            LireFichierGrille(nomFichierGrille);
            LireFichierDict(nomFichierDict);
            //ImprimerGrille();
            //System.out.println();
            //ImprimerDict();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return new String[0];
    }

    public void LireFichierGrille(String nomFichierGrille) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(nomFichierGrille));
        String line;
        int i = -1;
        while ((line = br.readLine()) != null) {
            if (i == -1) {
                this.grosseurGrille = Integer.parseInt(line);
                this.grille = new Character[this.grosseurGrille][this.grosseurGrille];
            } else {
                for (int j = 0; j < line.length(); j++) {
                    this.grille[i][j] = line.charAt(j);
                }
            }
            i++;
        }
        br.close();
    }

    public void LireFichierDict(String nomFichierDict) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(nomFichierDict));
        String line;
        int i = 0;
        while ((line = br.readLine()) != null) {
            motsATrouver.add(line);
            i++;
        }
        br.close();
    }

    public void ImprimerGrille() {
        for (int row = 0; row < grille.length; row++) {
            for (int col = 0; col < grille[row].length; col++) {
                System.out.print(grille[row][col]);
            }
            System.out.println();
        }
    }

    public void ImprimerDict() {
        for (String mot : this.motsATrouver) {
            System.out.println(mot);
        }
    }

}
