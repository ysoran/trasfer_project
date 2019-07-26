package moneytransfer.constants;

public class Constants {
	public enum Routes{
		EFT,
		TRANSFER
	}
	
	public enum TransferType{
		EFT,
		TRANSFER
	}
	
	public enum URLS{
		BASE_URL("http://localhost:8080/transfer");
		
		private String value;
		
		public String getValue(){
			return this.value;
		}
		
		private URLS(String value){
			this.setValue(value);
		}
		
		private void setValue(String value){
			this.value = value;
		}
	}
	
	public enum TransactionStatus{
		READY(0),
		IN_PROGRESS(1),
		REJECTED(2),
		COMPLETED(3);
		
		private int value;
		
		public int getValue(){
			return this.value;
		}
		
		private TransactionStatus(int value){
			this.setValue(value);
		}
		
		private void setValue(int value){
			this.value = value;
		}
	}
}
