package pasa.cbentley.jpasc.pcore.network.tests;

import pasa.cbentley.core.src4.structs.synch.FairLock;
import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.network.RPCConnection;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;

public class TestRPCConnection extends TestPCoreAbstract {

   public TestRPCConnection() {
   }

   public void setupAbstract() {
      super.setupAbstract(); //setup with connection
   }

   public void testBasic() {

      RPCConnection rpc = pc.getRPCConnection();
      assertEquals(4003, rpc.getPortAsIntValue());
      assertEquals("127.0.0.1", rpc.getIP());
      assertEquals(true, rpc.isConnected());
      assertEquals(0, rpc.getNumConnectionSlaves());
   }

   public void testClient() {

      RPCConnection rpc = pc.getRPCConnection();

      IPascalCoinClient client = rpc.getPClient();

      assertEquals(1, rpc.getNumConnectionSlaves());

      Account account = client.getAccount(0);
      assertNotNull(account);
   }

   public void testMultiThread() {

      RPCConnection rpc = pc.getRPCConnection();
      
      Runnable run1 = new Runnable() {

         public void run() {
            Thread.currentThread().setName("run1");
            IPascalCoinClient client = rpc.getPClient();

            try {
               for (int i = 0; i < 100; i++) {
                  Account account = client.getAccount(i);
                  assertNotNullThread(account);
                  
                  //#debug
                  toDLog().pTest("Account #"+account.getAccount() + " ", null, TestRPCConnection.class, "testMultiThread", LVL_05_FINE, true);
          
               }
            } catch (Exception e) {
               e.printStackTrace();
               assertNotReachableThread("break in run1");
            }

            rpc.threadClosingDown();
            //once finished.. notifies the test 
            lockRelease("1");
         }
      };

      Runnable run2 = new Runnable() {

         public void run() {
            Thread.currentThread().setName("run2");
            IPascalCoinClient client = rpc.getPClient();

            try {
               for (int i = 0; i < 100; i++) {
                  Integer blockCount = client.getBlockCount();
                  assertNotNullThread(blockCount);
                  Integer pendings = client.getPendingsCount();
                  assertNotNullThread(pendings);
                  
                  //#debug
                  toDLog().pTest(blockCount + " " + pendings, null, TestRPCConnection.class, "testMultiThread", LVL_05_FINE, true);
               }
            } catch (Exception e) {
               e.printStackTrace();
               assertNotReachableThread("break in run2");
            }

            rpc.threadClosingDown();
            //once finished.. notifies the test 
            lockRelease("2");
         }
      };

      //launch the threads
      setNunLockReleased(2);
      
      super.execute(run1, run2);
      
      super.lockWait(10000, "Wait");
      
      assertEquals(0, rpc.getNumConnectionSlaves());
   }

  
}
