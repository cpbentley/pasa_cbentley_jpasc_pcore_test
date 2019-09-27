package pasa.cbentley.jpasc.pcore.tools.tests;

import pasa.cbentley.core.src4.logging.ITechTags;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;
import pasa.cbentley.jpasc.pcore.tools.KeyNameProvider;

public class TestKeyNameProvider extends TestPCoreAbstract {

   private String fileNamePrefix = "testNameProvider";
   
   private  KeyNameProvider prov;
   
   public TestKeyNameProvider() {
      super(false);
   }

   /**
    * We don't need RPC connection for testing this class
    */
   public void setupAbstract() {
      super.setUpNoConnection();
      prov = new KeyNameProvider(pc,fileNamePrefix);
      pc.setKeyNameProvider(prov);
      config.setFlagTag(ITechTags.FLAG_20_PRINT_INIT, true);
   }

   public void testProvider() {

      assertEquals(true, prov.isPrivateMode());

      prov.setPkName("EncodedKey1", "name1Private", true);
      prov.setPkName("EncodedKey1", "name1Public", false);

      assertEquals("name1Private", prov.getKeyName("EncodedKey1"));

      prov.setPublic();

      assertEquals("name1Public", prov.getKeyName("EncodedKey1"));
   }

   public void testProviderNoKey() {
      assertEquals("key#1", prov.getKeyName("EncodedKey1"));
   }

   public void testProviderOnlyPublic() {
      prov.setPkName("EncodedKey1", "name1public", false);
      assertEquals("name1public", prov.getKeyName("EncodedKey1"));
   }
   
   
   public void testProviderPrivatePublicMix() {
      prov.setPkName("EncodedKey1", "name1public", false);
      prov.setPkName("EncodedKey1", "name1private", true);
      prov.setPkName("EncodedKey2", "name2private", true);
      prov.setPkName("EncodedKey3", "name3public", false);
      
      prov.setPublic();
      assertEquals("name1public", prov.getKeyName("EncodedKey1"));
      assertEquals("key#1", prov.getKeyName("EncodedKey2"));
      assertEquals("name3public", prov.getKeyName("EncodedKey3"));
      
      prov.setPrivate();
      assertEquals("name1private", prov.getKeyName("EncodedKey1"));
      assertEquals("name2private", prov.getKeyName("EncodedKey2"));
      assertEquals("name3public", prov.getKeyName("EncodedKey3"));
      assertEquals("key#2", prov.getKeyName("EncodedKey4"));
      
      //#debug
      toDLog().pTest("KeyNameProvider", prov, TestKeyNameProvider.class, "testProviderPrivatePublicMix", LVL_05_FINE, false);

   }
}
