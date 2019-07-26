package moneytransfer.model;

public class TransactionWay {
	public TransactionWay(){
		
	}
	public TransactionWay(int from, int to){
		this.setFrom(from);
		this.setTo(to);
	}
	int from;
	int to;
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof TransactionWay){
			TransactionWay tw = (TransactionWay) o;
			if(from == tw.from && to == tw.to){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return from*to;
	}
}
