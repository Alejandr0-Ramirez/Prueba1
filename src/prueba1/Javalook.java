package prueba1;

import java.util.Scanner;

public class Javalook {

    private static Emailaccount[]accounts=new Emailaccount[100];
    private static int accountCount=0;
    private static Emailaccount activeAccount;
    private static Scanner lea=new Scanner(System.in);

    public static void main(String[] args) {
        menuInicial();
    }
    private static void menuInicial(){
        boolean running=true;
        while(running){
            System.out.println("\n Menu de inicio");
            System.out.println("1. Login");
            System.out.println("2. Crear cuenta");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            String option=lea.nextLine();

            switch(option){
                case "1":
                    login();
                    break;
                case "2":
                    createAccount();
                    break;
                case "0":
                    System.out.println("Hasta luego.");
                    running=false;
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }
    private static void login(){
        System.out.print("Correo: ");
        String email=lea.nextLine();
        System.out.print("Password: ");
        String password=lea.nextLine();
        for(int i=0;i<accountCount;i++){
            if(accounts[i].getEmail().equalsIgnoreCase(email)&& accounts[i].getPassword().equals(password)){
                activeAccount=accounts[i];
                System.out.println("Bienvenido, "+activeAccount.getName()+"!");
                menuPrincipal();
                return;
            }
        }
        System.out.println("Credenciales incorrectas.");
    }
    private static void createAccount(){
        if(accountCount>=accounts.length){
            System.out.println("No hay espacio para mas cuentas.");
            return;
        }
        
        System.out.print("Correo: ");
        String email=lea.nextLine();
        for(int i=0;i<accountCount;i++){
            if (accounts[i].getEmail().equalsIgnoreCase(email)){
                System.out.println("Ese correo ya esta registrado.");
                return;
            }
        }
        System.out.print("Nombre: ");
        String name=lea.nextLine();
        System.out.print("Password: ");
        String password = lea.nextLine();
        accounts[accountCount]=new Emailaccount(email,password,name);
        activeAccount=accounts[accountCount];
        accountCount++;
        System.out.println("Cuenta creada. Bienvenido, " + name + "!");
        menuPrincipal();
    }

    private static void menuPrincipal(){
        boolean running=true;
        while(running){
            System.out.println("\n MENU PRINCIPAL");
            System.out.println("1. Ver inbox");
            System.out.println("2. Mandar correo");
            System.out.println("3. Leer un correo");
            System.out.println("4. Limpiar inbox");
            System.out.println("0. Cerrar sesion");
            System.out.print("Opcion: ");

            String option = lea.nextLine();
            switch(option){
                case "1":
                    activeAccount.showInbox();
                    break;
                case "2":
                    sendEmail();
                    break;
                case "3":
                    readEmail();
                    break;
                case "4":
                    activeAccount.deleteRead();
                    break;
                case "0":
                    System.out.println("Sesion cerrada.");
                    activeAccount=null;
                    running=false;
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }
    private static void sendEmail(){
        System.out.print("Destinatario: ");
        String toEmail=lea.nextLine();
        Emailaccount recipient=null;
        for(int i=0;i<accountCount;i++){
            if(accounts[i].getEmail().equalsIgnoreCase(toEmail)){
                recipient=accounts[i];
                break;
            }
        }
        if(recipient==null){
            System.out.println("El destinatario no existe.");
            return;
        }
        System.out.print("Asunto: ");
        String subject=lea.nextLine();
        System.out.print("Mensaje: ");
        String body=lea.nextLine();
        Email newEmail=new Email(activeAccount.getEmail(),subject,body);
        boolean sent=recipient.receiveEmail(newEmail);

        if(sent){
            System.out.println("Correo enviado correctamente.");
        } else{
            System.out.println("No se pudo enviar: inbox del destinatario lleno.");
        }
    }
    private static void readEmail(){
        System.out.print("Posicion del correo: ");
        int input=lea.nextInt();
        int position=input;
        activeAccount.readEmail(position);
        
    }
}
