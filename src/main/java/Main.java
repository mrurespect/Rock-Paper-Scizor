import java.io.FileNotFoundException;
import java.util.* ;
import java.io.File ;
public class Main {
    public static String[] compare(String str,String choice){
        int i=0 ;
        String[] array;
        String []array22=str.split(",");
        for (String element:array22     ) {
            if (element.equals(choice))break;;
            i++;
        }
        String str2 ;
        if(i==0){
            String sep =choice.concat(",");
            array =str.split(sep);
            str2=array[1];
        }
        else
        if (i== array22.length-1){
            array =str.split(","+choice);
            str2=array[0];
        }
        else {
            array =str.split(","+choice+",");
            str2=array[1].concat(",").concat(array[0]);
        }

        return str2.split(",");
    }
    public static void play(int score,String[] choices,String str){
        Scanner sc = new Scanner(System.in);
        Random random =new Random();
        String choice ;
        do{ int i=0 ;
            while(true){
                choice = sc.nextLine();
                if (choice.equals("!exit")){
                    System.out.println("Bye!") ;
                    i++;
                    break ;
                }
                if (choice.equals("!rating")){
                    System.out.printf("Your rating: %d\n",score);
                    continue;
                }
                int j=0;
                for (String element:choices  ) {
                    if (choice.equals(element))j++;
                }
                if (j==0){
                    System.out.println("Invalid input");
                    continue ;
                }
                break ;
            }
            if (i!=0)break ;
            String output=null ;
            int pos=0;
            String computer = choices[random.nextInt(choices.length)];
            if(choice.equals(computer)){
                output=String.format("There is a draw (%s)",computer);
                score+=50 ;
            }
            else {
                String []array=compare(str,choice );
                for (String element:array ) {
                    if (element.equals(computer))break;
                    i++;
                }
                if(i<(array.length)/2)output=String.format("Sorry, but the computer chose %s",computer);
                else {
                    output=String.format("Well done. The computer chose %s and failed", computer);
                    score+=100;
                 }
            }
            System.out.println(output);
        }while(true) ;
    }
    public static void playsmart(int score){
        Scanner sc = new Scanner(System.in);
        Random random =new Random();
        String choice ;
        do{ int i=0 ;
            while(true){
                choice = sc.nextLine();
                if (choice.equals("!exit")){
                    System.out.println("Bye!") ;
                    i++;
                    break ;
                }
                if (choice.equals("!rating")){
                    System.out.printf("Your rating: %d\n",score);
                    continue;
                }
                if(!choice.equals("paper")&&!choice.equals("rock")&&!choice.equals("scissors")){
                    System.out.println("Invalid input");
                    continue ;
                }
                break ;
            }
            if (i!=0)break ;
            String[] choices ={"paper","scissors","rock"};
            String output=null ;
            String computer = choices[random.nextInt(3)];
            if(choice.equals(computer)){
                output=String.format("There is a draw (%s)",computer);
                score+=50 ;
            }
            else
            if(choice.equals(choices[0]) && computer.equals(choices[1]))output="Sorry, but the computer chose scissors";
            else
            if(choice.equals(choices[1]) && computer.equals(choices[2]))output="Sorry, but the computer chose rock";
            else
            if(choice.equals(choices[2]) && computer.equals(choices[0]))output="Sorry, but the computer chose paper";
            else {
                output = String.format("Well done. The computer chose %s and failed", computer);
                score+=100;
            }
            System.out.println(output);
        }while(true) ;
    }
    public static void main(String[] args) throws FileNotFoundException {
        System.out.print("Enter your name:");
        Scanner sc =new Scanner(System.in);
        String name = sc.next();
        System.out.printf("Hello, %s\n",name);
        File file =new File("rating.txt");
        Scanner sf =new Scanner(file);
        int score =0;
        while (sf.hasNext()){
            if (name.equals(sf.next()))score=sf.nextInt();
        }
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        if (str.equals("")){
            String[] choices ={"paper","scissors","rock"};
            System.out.println("Okay, let's start");
            playsmart(score);
        }
        else {
            String[] choices = str.split(",");
            System.out.println("Okay, let's start");
            play(score,choices,str);
        }
    }
}
