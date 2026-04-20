package prueba1;

public class Emailaccount {

    private String email;
    private String password;
    private String name;
    private Email[] inbox;
    private int count;
    public Emailaccount(String email, String password, String name){
        this.email=email;
        this.password=password;
        this.name=name;
        this.inbox=new Email[100];
        this.count=0;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getName(){
        return name;
    }
    
    public boolean receiveEmail(Email e){
        if(count>= inbox.length){
            return false;
        }
        inbox[count]=e;
        count++;
        return true;
    }
    public void showInbox(){
        System.out.println("Cuenta:"+email);
        System.out.println("Nombre:"+name);
        int unread=0;
        for(int i=0;i<inbox.length;i++){
         if(inbox[i]!=null){
            String status=inbox[i].isRead()?"LEIDO":"SIN LEER";
          System.out.println((i+1)+" - "+inbox[i].getSender()+" - "+inbox[i].getSubject()+ " - "+status);
          if(!inbox[i].isRead()){
            unread++;
                }
            }
        }
        System.out.println("Sin leer:"+unread);
        System.out.println("Total: "+count);
    }

    public void readEmail(int position){
        int index=position-1;
        if(index<0||index>=inbox.length||inbox[index]==null){
            System.out.println("Correo No Existe");
            return;
        }
        inbox[index].print();
        inbox[index].markAsRead();
    }

    public void deleteRead(){
    int removed=0;
    for(int i=0; i <inbox.length;i++){
      if(inbox[i]!=null && inbox[i].isRead()){
          inbox[i]=null;
          count--;
          removed++;
         }
       }
        System.out.println("Correos eliminados: "+removed);
    }
}
