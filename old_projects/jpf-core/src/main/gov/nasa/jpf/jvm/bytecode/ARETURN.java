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

import gov.nasa.jpf.jvm.DynamicArea;
import gov.nasa.jpf.jvm.ThreadInfo;
import gov.nasa.jpf.jvm.Types;

import org.apache.bcel.classfile.ConstantPool;


/**
 * Return reference from method
 * ..., objectref  => [empty]
 */
public class ARETURN extends ReturnInstruction {
  int ret;
  
  public void setPeer (org.apache.bcel.generic.Instruction i, ConstantPool cp) {
  }

  protected void storeReturnValue (ThreadInfo th) {
    ret = th.pop();
  }
  
  protected void pushReturnValue (ThreadInfo th) {
    th.push(ret, true);
  }

  public int getReturnValue () {
    return ret;
  }
  
  public Object getReturnValue(ThreadInfo ti) {
    if (!isCompleted(ti)) { // we have to pull it from the operand stack
      ret = ti.peek();
    }
    
    if (ret == -1) {
      return null;
    } else {
      return DynamicArea.getHeap().get(ret);
    }
  }
  
  public int getByteCode () {
    return 0xB0;
  }
  
  public String toString() {
    return "areturn " + mi.getFullName();
  }
  
  public void accept(InstructionVisitor insVisitor) {
	  insVisitor.visit(this);
  }

}
