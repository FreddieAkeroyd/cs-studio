package org.csstudio.opibuilder.pvmanager;
import static org.epics.pvmanager.data.ExpressionLanguage.vNumber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.epics.pvmanager.PVManager;
import org.epics.pvmanager.PVReader;
import org.epics.pvmanager.PVReaderListener;
import org.epics.pvmanager.data.VNumber;
import org.epics.pvmanager.loc.LocalDataSource;
import org.epics.util.time.TimeDuration;

public class MultipleListenersWithNoGUI {
        private static Text textA;
        private static Text textB;
        private static PVReader<VNumber> pv;

        /**
         * @param args
         * @throws InterruptedException
         */
        public static void main(String[] args) throws InterruptedException {

                final ExecutorService executor = Executors.newSingleThreadExecutor();


                PVManager.setDefaultDataSource(new LocalDataSource());  
                   
                
                executor.execute(new Runnable() {
					
					@Override
					public void run() {
						pv= PVManager.read(vNumber("loc://test")).notifyOn(executor).
                		maxRate(TimeDuration.ofMillis(5));
					}
				});
                
                
//                for(long i=0; i<1000000000l; i++){
//                	//do my stuff
//                }
                Thread.sleep(100);

                executor.execute(new Runnable() {
					
					@Override
					public void run() {
						 pv.addPVReaderListener(new PVReaderListener() {

		                        @Override
		                        public void pvChanged() {
		                                if (pv.getValue() != null)
		                                	System.out.println("First: " + pv.getValue().getValue().toString() + " " + Thread.currentThread().getName());
		                        }
		                });
						
					}
				});
                
               
           //     Thread.sleep(10);
                executor.execute(new Runnable() {
					
					@Override
					public void run() {
						pv.addPVReaderListener(new PVReaderListener() {

	                        @Override
	                        public void pvChanged() {
	                                if (pv.getValue() != null)
	                                	System.out.println("Second: " + pv.getValue().getValue().toString() + " " + Thread.currentThread().getName());

	                        }
	                });						
					}
				});
                
                Thread.sleep(5000);

                executor.shutdown();
        }
}