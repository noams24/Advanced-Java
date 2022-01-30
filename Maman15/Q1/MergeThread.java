
public class MergeThread implements Runnable {
	private int[] firstArr;
	private int[] secondArr;
	private int[] resultArr;
	private Pool pool;
	
	public MergeThread(int[] first,int[] sec,Pool pool) {
		firstArr =first;
		secondArr =sec;
		resultArr = new int[first.length+sec.length];
		this.pool =pool;
	}
	
	@Override
	public void run() {
		int i=0,j=0,z=0;
		while(i< firstArr.length && j< secondArr.length) {
			if(firstArr[i]< secondArr[j])
				resultArr[z++]= firstArr[i++];
			else
				resultArr[z++]= secondArr[j++];
		}
		while(i< firstArr.length) {
			resultArr[z++]= firstArr[i++];
		}
		while(j< secondArr.length) {
			resultArr[z++]= secondArr[j++];
		}
		pool.push(resultArr);
	}
}
