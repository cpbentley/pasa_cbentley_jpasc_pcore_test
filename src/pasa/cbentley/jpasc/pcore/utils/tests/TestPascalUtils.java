/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.utils.tests;

import org.junit.Test;

import pasa.cbentley.core.src5.ctx.C5Ctx;
import pasa.cbentley.jpasc.pcore.TestPCoreAbstract;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.utils.PascalUtils;
import pasa.cbentley.testing.BentleyTestCase;

public class TestPascalUtils extends TestPCoreAbstract {

   private PascalUtils pu;

   public void setupAbstract() {
      super.setupAbstract();
      pu = pc.getPU();
   }

   public void testPascal64StringEncode_Bomb() throws Exception {
      byte[] data = pu.pascal64StringEncode("abcdef");
      System.out.println("Encoded Bytes = " + uc.getBU().toStringBytes(data, " "));
      try {
         pu.pascal64StringDecode(data, 1);
         super.assertNotReachable("Must throw an exception. offset is bad");
      } catch (ArrayIndexOutOfBoundsException e) {
         assertTrue(true);
      }
   }

   public void testPascal64StringEncode_Max63() throws Exception {

      assertEquals(64, PascalUtils.PASCAL64_CHARS.length());
      try {
         pu.pascal64StringEncode(PascalUtils.PASCAL64_CHARS);
         super.assertNotReachable("Maximum 63 chars");
      } catch (IllegalArgumentException e) {
         assertTrue(true);
      }

   }

   public void testPascal64StringEncode_Special() {
      String str = "!@#$%^&*()-+{}[]_:\\\"|<>,.?/~";
      byte[] data = pu.pascal64StringEncode(str);
      String decoded = pu.pascal64StringDecode(data, 0);
      System.out.println(decoded);
      assertEquals(str, decoded);
   }

   public void testPascal64StringEncode_Sub63() throws Exception {

      assertEquals(64, PascalUtils.PASCAL64_CHARS.length());
      byte[] data = pu.pascal64StringEncode(PascalUtils.PASCAL64_CHARS, 1, 20);
      String decoded = pu.pascal64StringDecode(data, 0);
      System.out.println(decoded);
      assertEquals(PascalUtils.PASCAL64_CHARS.substring(1, 20), decoded);

   }

   public void testPascal64StringEncode_InvalidChar() throws Exception {
      try {
         pu.pascal64StringEncode("ab cdef");
         super.assertNotReachable("Only valid chars");
      } catch (IllegalArgumentException e) {
         assertTrue(true);
      }
   }
   
   public void testPascal64StringEncode_abcdef() throws Exception {

      byte[] data = pu.pascal64StringEncode("abcdef");

      System.out.println("Encoded Bytes = " + uc.getBU().toStringBytes(data, " "));

      String decoded = pu.pascal64StringDecode(data, 0);
      System.out.println(decoded);

      assertEquals("abcdef", decoded);
   }

   @Test
   public void testPascal64StringEncode_bonjour() throws Exception {

      byte[] data = pu.pascal64StringEncode("bonjour");

      System.out.println("Encoded Bytes = " + uc.getBU().toStringBytes(data, " "));

      String decoded = pu.pascal64StringDecode(data, 0);
      System.out.println(decoded);

      assertEquals("bonjour", decoded);
   }
}
