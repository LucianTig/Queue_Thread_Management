import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class SimulatorManager extends Thread{
	private int nrC;
	private Coada[] cozi;
	private Vector<Client> magazin=new Vector<Client>();
	private Vector<Client> copieLista=new Vector<Client>();
	private JTextArea text,simResult,logEvents;
	private int simTime;

	public void generareClient(int minArr, int maxArr, int minSer, int maxSer, int nrClienti, int nrCozi, int simTime) {
		this.cozi=new Coada[nrCozi];
		nrC=nrCozi;
		this.simTime=simTime;

		for(int i=0;i<nrC;i++)
			cozi[i]=new Coada(i);

		Random rand=new Random();
		for(int i=0;i<nrClienti;i++) {
			int arTime=rand.nextInt(maxArr-minArr+1)+minArr;
			int serTime=rand.nextInt(maxSer-minSer+1)+minSer;
			System.out.println("Clientul "+(i+1)+" arrivalTime="+arTime+" serviceTime="+serTime);
			Client cl=new Client(serTime, arTime, i+1);
			magazin.add(cl);
			copieLista.add(cl);
		}	
	}

	public void run(){ 
		try{ 
			int sum=0;
			int contor=0;
			int peakTime=0;
			this.startCozi();
			while( simTime>=0) {
				if(magazin.size()>0) {
					Vector<Client> listaTemp=new Vector<Client>();
					for(Client a:magazin) {
						a.setArrTime(a.getArrTime()-1);
						if(a.getArrTime()==0) {
							listaTemp.add(a);
							this.gestionareClient(a);
						}
					}
					for(Client a:listaTemp) 
						magazin.remove(a);
				}

				String te="";
				for(int i=0;i<nrC;i++) {
					te=te+cozi[i].toString()+"\n\n";
				}
				text.setText(te);
				if(this.peakT(sum)>sum) {
					peakTime=contor;
				}
				simTime--;
				contor++;
				if(simTime==0) {
					this.stopCozi();
					this.AveWait();
					simResult.setText(simResult.getText()+"\n"+"Peak time="+peakTime);
					for(int i=0;i<nrC;i++) {
						logEvents.setText(logEvents.getText()+cozi[i].getLogEvents()+"\n");
						logEvents.setText(logEvents.getText()+"\n");
					}
				}

				sleep(1000);
			} 
		} 
		catch( InterruptedException e ){ 
			System.out.println("Intrerupere"+ e.toString()); 
		} 
	}

	public void gestionareClient(Client cl) {
		int min=cozi[0].getSize();
		int j=0;
		for(int i=0;i<nrC;i++)
			if(cozi[i].getSize()<min) {
				min=cozi[i].getSize();
				j=i;
			}
		cozi[j].addClient(cl);

	}

	public int peakT(int sum) {
		int sum1=0;
		for(int i=0;i<nrC;i++) {
			int a=cozi[i].getSize();
			sum1+=a;
		}
		if(sum1>sum) 
			return sum1;
		return sum;

	}

	public void startCozi() {
		for(int i=0;i<nrC;i++)
			cozi[i].start();
	}

	public void stopCozi() {
		for(int i=0;i<nrC;i++)
			cozi[i].stopThread();
	}

	public void setRef(JTextArea a,JTextArea b,JTextArea c) {
		text=a;
		simResult=b;
		logEvents=c;
	}

	public void AveWait() {
		double av=0;
		int i=0;
		for(Client a:copieLista) {
			av=av+a.getWTime();
			if(a.getSerTime()==0) 
				i++;
		}
		av=av/i;
		simResult.setText("Average waiting time: "+ av);
	}
}
