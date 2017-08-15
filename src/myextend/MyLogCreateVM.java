package myextend;

public class MyLogCreateVM {
	private int totalVm;
	private int numVMCreateSuccess;
	private int numVMCreateFail;
	private int numVMNoneCreate;
	private int numDC;
	public MyLogCreateVM(int totalVm, int numVMCreateSuccess,
			int numVMCreateFail, int numVMNoneCreate, int numDC) {
		this.totalVm = totalVm;
		this.numVMCreateSuccess = numVMCreateSuccess;
		this.numVMCreateFail = numVMCreateFail;
		this.numVMNoneCreate = numVMNoneCreate;
		this.numDC = numDC;
	}
	
	
	public MyLogCreateVM(int totalVm, int numVMCreateSuccess,
			int numVMCreateFail, int numVMNoneCreate) {
		super();
		this.totalVm = totalVm;
		this.numVMCreateSuccess = numVMCreateSuccess;
		this.numVMCreateFail = numVMCreateFail;
		this.numVMNoneCreate = numVMNoneCreate;
	}


	public int getNumDC() {
		return numDC;
	}

	public void setNumDC(int numDC) {
		this.numDC = numDC;
	}

	public int getTotalVm() {
		return totalVm;
	}
	public void setTotalVm(int totalVm) {
		this.totalVm = totalVm;
	}
	public int getNumVMCreateSuccess() {
		return numVMCreateSuccess;
	}
	public void setNumVMCreateSuccess(int numVMCreateSuccess) {
		this.numVMCreateSuccess = numVMCreateSuccess;
	}
	public int getNumVMCreateFail() {
		return numVMCreateFail;
	}
	public void setNumVMCreateFail(int numVMCreateFail) {
		this.numVMCreateFail = numVMCreateFail;
	}
	public int getNumVMNoneCreate() {
		return numVMNoneCreate;
	}
	public void setNumVMNoneCreate(int numVMNoneCreate) {
		this.numVMNoneCreate = numVMNoneCreate;
	}
	@Override
	public String toString() {
		return "[" +totalVm + ", " + numVMCreateSuccess +", " + numVMNoneCreate + ", " + numVMCreateFail +"]" + " - " + numDC;
	}
	
}
