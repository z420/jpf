//
// Copyright (C) 2006 United States Government as represented by the
// Administrator of the National Aeronautics and Space Administration
// (NASA).  All Rights Reserved.
//
// This software is distributed under the NASA Open Source Agreement
// (NOSA), version 1.3.  The NOSA has been approved by the Open Source
// Initiative.  See the file NOSA-1.3-JPF at the top of the distribution
// directory tree for the complete NOSA document.
//
// THE SUBJECT SOFTWARE IS PROVIDED "AS IS" WITHOUT ANY WARRANTY OF ANY
// KIND, EITHER EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT
// LIMITED TO, ANY WARRANTY THAT THE SUBJECT SOFTWARE WILL CONFORM TO
// SPECIFICATIONS, ANY IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
// A PARTICULAR PURPOSE, OR FREEDOM FROM INFRINGEMENT, ANY WARRANTY THAT
// THE SUBJECT SOFTWARE WILL BE ERROR FREE, OR ANY WARRANTY THAT
// DOCUMENTATION, IF PROVIDED, WILL CONFORM TO THE SUBJECT SOFTWARE.
//
package gov.nasa.jpf.jvm.bytecode;

import gov.nasa.jpf.jvm.ThreadInfo;

import org.apache.bcel.classfile.ConstantPool;


/**
 * Return long from method
 * ..., value => [empty]
 */
public class LRETURN extends ReturnInstruction {

  long ret;

  public Object getReturnAttr (ThreadInfo ti) {
    return ti.getLongOperandAttr();
  }

  public void setReturnAttr (ThreadInfo ti, Object attr){
    ti.setLongOperandAttrNoClone(attr);
  }

  public void setPeer (org.apache.bcel.generic.Instruction i, ConstantPool cp) {
  }

  protected void storeReturnValue (ThreadInfo th) {
    ret =  th.longPop();
  }

  protected void pushReturnValue (ThreadInfo th) {
    th.longPush(ret);
  }

  public long getReturnValue () {
    return ret;
  }

  public Object getReturnValue(ThreadInfo ti) {
    if (!isCompleted(ti)) { // we have to pull it from the operand stack
      ret = ti.longPeek();
    }

    return new Long(ret);
  }

  public int getByteCode () {
    return 0xAD;
  }

  public String toString() {
    return "lreturn " + mi.getFullName();
  }

  public void accept(InstructionVisitor insVisitor) {
	  insVisitor.visit(this);
  }
}
