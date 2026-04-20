package prueba1;

public class Email{
    
    private String sender;
    private String subject;
    private String body;
    private boolean read;
    
    public Email(String sender, String subject, String body){
        this.sender=sender;
        this.subject=subject;
        this.body=body;
        this.read=false;
    }
    
    public String getSender() {return sender;}
    public String getSubject() {return subject;}
    public String getBody() { return body;}
    public boolean isRead() {return read;}
    
    public void markAsRead(){
       read = true; 
    }
    
    public void print() {
        System.out.println("DE: "+sender);
        System.out.println("ASUNTO: "+subject);
        System.out.println(body);
    }
}