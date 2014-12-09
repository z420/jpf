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

import gov.nasa.jpf.jvm.KernelState;
import gov.nasa.jpf.jvm.SystemState;
import gov.nasa.jpf.jvm.ThreadInfo;

import org.apache.bcel.classfile.ConstantPool;


/**
 * Jump subroutine (wide index)
 * ... => ..., address
 */
public class JSR_W extends Instruction {
  private int target;

  public void setPeer (org.apache.bcel.generic.Instruction i, ConstantPool cp) {
    target = ((org.apache.bcel.generic.JSR_W) i).getTarget().getPosition();
  }

  public Instruction execute (SystemState ss, KernelState ks, ThreadInfo th) {
    th.push(getNext(th).getPosition(), false);

    return th.getMethod().getInstructionAt(target);
  }

  public int getLength() {
    return 5; // opcode, bb1, bb2, bb3, bb4
  }
  
  public int getByteCode () {
    return 0xC9;
  }
  
  public void accept(InstructionVisitor insVisitor) {
	  insVisitor.visit(this);
  }
}
