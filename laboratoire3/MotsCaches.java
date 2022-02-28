package laboratoire3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MotsCaches {
    int grosseurGrille;
    Character[][] grille;
    HashMap<Character, ArrayList<String>> motsATrouver = new HashMap<Character, ArrayList<String>>();
    ArrayList<String> motsTrouves = new ArrayList<String>();

    public String[] Resoudre(String nomFichierGrille, String nomFichierDict) {
        try {
            LireFichierGrille(nomFichierGrille);
            LireFichierDict(nomFichierDict);
            ParcourirTableau();
            Collections.sort(motsTrouves);
            return motsTrouves.toArray(new String[motsTrouves.size()]);
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
        while ((line = br.readLine()) != null) {
            Character currentStartChar = line.charAt(0);
            ArrayList<String> currentWords = new ArrayList<String>();
            if (motsATrouver.containsKey(currentStartChar)) {
                currentWords = motsATrouver.get(currentStartChar);
            }
            currentWords.add(line);
            motsATrouver.put(line.charAt(0), currentWords);
        }
        br.close();
    }

    public void ParcourirTableau() {
        for (int row = 0; row < grille.length; row++) {
            for (int col = 0; col < grille[row].length; col++) {
                Character currentStartChar = grille[row][col];
                if (motsATrouver.containsKey(currentStartChar)) {
                    for (String word : this.motsATrouver.get(currentStartChar)) {
                        for (int i = 0; i < CheckPositionWord(word, row, col); i++) {
                            motsTrouves.add(word);
                        }
                    }
                }
            }
        }
    }

    private int CheckPositionWord(String word, int x, int y) {
        boolean droite = true, gauche = true,  haut = true, bas = true, hautDroite = true, basDroite = true, hautGauche = true, basGauche = true;
        int nbFoisTrouvee = 0;
        for (int i = 1; i < word.length(); i++) {
            droite = (droite && x + i < grosseurGrille) ? grille[x + i][y] == word.charAt(i) : false;
            gauche = (gauche && x - i >= 0) ? grille[x - i][y] == word.charAt(i) : false;
            bas = (bas && y + i < grosseurGrille) ? grille[x][y + i] == word.charAt(i) : false;
            haut = (haut && y - i >= 0) ? grille[x][y - i] == word.charAt(i) : false;
            hautDroite = (hautDroite && x + i < grosseurGrille && y - i >= 0) ? grille[x + i][y - i] == word.charAt(i) : false;
            basDroite = (basDroite && x + i < grosseurGrille && y + i < grosseurGrille) ? grille[x + i][y + i] == word.charAt(i) : false;
            hautGauche = (hautGauche && x - i >= 0 && y - i >= 0) ? grille[x - i][y - i] == word.charAt(i) : false;
            basGauche = (basGauche && x - i >= 0 && y + i < grosseurGrille) ? grille[x - i][y + i] == word.charAt(i) : false;
            if(!droite && !gauche && !haut && !bas && !hautDroite && !basDroite && !hautGauche && !basGauche){
                break;
            }
        }
        
        nbFoisTrouvee += droite ? 1: 0;
        nbFoisTrouvee += gauche ? 1 : 0;
        nbFoisTrouvee += haut ? 1 : 0;
        nbFoisTrouvee += bas ? 1 : 0;
        nbFoisTrouvee += hautDroite ? 1 : 0;
        nbFoisTrouvee += basDroite ? 1 : 0;
        nbFoisTrouvee += hautGauche ? 1 : 0;
        nbFoisTrouvee += basGauche ? 1 : 0;

        return nbFoisTrouvee;
    }
}
