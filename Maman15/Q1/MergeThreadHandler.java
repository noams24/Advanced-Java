import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class MergeThreadHandler {
	private int numOfThreads;
	private Pool pool;

	public MergeThreadHandler(int numOfThreads,int sizeOfArr,int[] initialArr) {
		this.numOfThreads =numOfThreads;
		pool =new Pool(sizeOfArr);
		for(int j=0;j<sizeOfArr;j++)
		{
			int[] temp =new int[1];
			temp[0]=initialArr[j];
			pool.push(temp);
		}
	}
	
	public int[] runMergeThread() {
		try
		{
			ExecutorService exe = Executors.newFixedThreadPool(numOfThreads);
			while(pool.canContinue())
				exe.execute(new MergeThread(pool.pop(), pool.pop(), pool));
			exe.shutdown();
			exe.awaitTermination(Long.MAX_VALUE,TimeUnit.SECONDS);
		}
		catch(InterruptedException e)
		{	
			e.printStackTrace();
			System.exit(1);
		}
		return pool.pop();
	}
}
