import java.awt.List;
import java.util.Vector;

public class Coada extends Thread{
	private Vector<Client> coada= new Vector<Client>();
	private int index;
	private boolean exit=false;
	private String logEvents;
	private int min=0;

	public Coada(int index) {
		this.index=index;
		logEvents="Coada"+this.index+"\n";
	}

	public void run(){ 
		try{ 
			while(!exit ){ 
				if(coada.size()>0) {

					synchronized(this) {
						for(Client a:coada) 
							a.setWTime();
					}
					coada.get(0).setSerTime();
					sleep(1000);
					min++;
					//sleep(coada.get(0).getSerTime()*1000);
					if(coada.get(0).getSerTime()==0)
						removeClient();  
				}
			} 
		} 
		catch( InterruptedException e ){ 
			System.out.println("Intrerupere"+ e.toString()); 
		} 
	}

	public void stopThread(){
		exit=true;
	}

	public synchronized void addClient(Client a) {
		logEvents+="Client cu ID client "+a.getID()+" introdus in coada "+this.index+" la minutul "+min+ "\n";
		coada.add(a);
	}

	public synchronized void removeClient() throws InterruptedException {
		while(coada.size()==0)
			wait();
		logEvents+="Client cu ID client "+coada.get(0).getID()+" sters din coada "+this.index+" la minutul "+min+"  Timpul de asteptare este:"+coada.get(0).getWTime()+"\n";
		coada.remove(0);
	}

	public synchronized int getSize() {
		return coada.size();
	}
	
	public synchronized String getLogEvents() {
		return logEvents;
	}
	
	public synchronized String toString() {
		String str="Coada"+this.index+" ";
		for(Client a:coada) 
			str=str+a.getID()+" ";
		return str;
	}

}
