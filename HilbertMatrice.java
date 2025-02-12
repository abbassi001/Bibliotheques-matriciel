
import java.util.Scanner;

public class HilbertMatrice extends Matrice {

    public HilbertMatrice(int n) {
        super(n, n); 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.coefficient[i][j] = 1.0 / (i + j + 1);
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int ordre;

        // Saisie de l'ordre de la matrice avec vérification
        do {
            System.out.print("Veuillez entrer l'ordre de la matrice (entre 3 et 15) : ");
            ordre = sc.nextInt();
        } while (ordre < 3 || ordre > 15);

        // Création de la matrice de Hilbert
        HilbertMatrice hilbertMatrice = new HilbertMatrice(ordre);
        System.out.println("\nMatrice de Hilbert d'ordre " + ordre + " :");
        System.out.print(hilbertMatrice);
        Matrice identite = Matrice.identite(ordre);
        System.out.println("\nMatrice identité d'ordre " + ordre + " :");
        System.out.print(identite);

        Matrice copie= new Matrice(ordre,ordre);
        copie.recopie(hilbertMatrice);

        // calcul d inverse 
        Matrice inverse = hilbertMatrice.inverse();
        System.out.println("\nMatrice inverse :");
        System.out.print(inverse);

        // Vérificatio44n en comparant A * A^-1 avec l'identité
        Matrice produit = Matrice.produit(copie, inverse);
        // Matrice identite = Matrice.identite(ordre);
        System.out.println("\nProduit de la matrice initiale et de son inverse :");
        System.out.print(produit);

        // Calcul de difference de la norme 1
        Matrice diff = Matrice.addition(produit,identite.produit(-1));


        // condtionnement de la matrice
        System.out.println("Conditionnement 1 de la matrice : " + hilbertMatrice.cond_1());
        System.out.println("Conditionnement infini de de la matrice : " + hilbertMatrice.cond_inf());

        // calcul de norme 1
        System.out.println("difference entre valeur absolue de  produit - identite :" + Math.abs(diff.norme_1()));
        // calcul de norme infinie
        System.out.println("difference entre valeur absolue de  produit - identite :" + Math.abs(diff.norme_inf()));

        // condition de la norme 1
        if (Math.abs(diff.norme_1()) < Matrice.EPSILON) {
            System.out.println("***  La norme 1 est inférieur à epsilon ***");
        } else {
            System.out.println("*** La norme 1 est supérieur à epsilon ***");
        }

        // condition de la norme infinie
        if (Math.abs(diff.norme_inf()) < Matrice.EPSILON) {
            System.out.println("*** La norme infinie est inférieur à epsilon ***");
        } else {
            System.out.println("*** La norme infinie est supérieur à epsilon ***");
        }

        sc.close();
    }
}

    
        

