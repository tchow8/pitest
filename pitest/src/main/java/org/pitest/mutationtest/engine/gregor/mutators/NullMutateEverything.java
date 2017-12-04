package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.Arrays;
import java.util.List;

import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

/**
 * Creates "mutants" that contain no changes at all possible mutation
 * points.
 * 
 * Intended for testing purposes only.
 *
 */
public class NullMutateEverything implements MethodMutatorFactory {

  @Override
  public MethodVisitor create(MutationContext context, MethodInfo methodInfo,
      MethodVisitor methodVisitor) {
    return new MutateEveryThing(this, context, methodVisitor);
  }

  @Override
  public String getGloballyUniqueId() {
    return "mutateallthethings";
  }

  @Override
  public String getName() {
    return "mutateeverything";
  }
  
  public static List<MethodMutatorFactory> asList() {
    return Arrays.asList((MethodMutatorFactory) new NullMutateEverything());
  }

}

class MutateEveryThing extends MethodVisitor {
  private final MethodMutatorFactory factory;
  private final MutationContext      context;

  MutateEveryThing(final MethodMutatorFactory factory,
      final MutationContext context,
      final MethodVisitor delegateMethodVisitor) {
    super(Opcodes.ASM6, delegateMethodVisitor);
    this.factory = factory;
    this.context = context;
  }

  @Override
  public void visitIincInsn(final int var, final int increment) {
    mutate();
  }

  public void visitInsn(int opcode) {
    mutate();
  }

  public void visitIntInsn(int opcode, int operand) {
    mutate();
  }

  public void visitVarInsn(int opcode, int var) {
    mutate();
  }

  public void visitTypeInsn(int opcode, String type) {
    mutate();
  }

  public void visitFieldInsn(int opcode, String owner, String name,
      String desc) {
    mutate();
  }

  public void visitMethodInsn(int opcode, String owner, String name,
      String desc, boolean itf) {
    mutate();
  }

  public void visitInvokeDynamicInsn(String name, String desc, Handle bsm,
      Object... bsmArgs) {
    mutate();
  }

  public void visitJumpInsn(int opcode, Label label) {
    mutate();
  }

  public void visitLdcInsn(Object cst) {
    mutate();
  }

  public void visitTableSwitchInsn(int min, int max, Label dflt,
      Label... labels) {
    mutate();
  }

  public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
    mutate();
  }

  public void visitMultiANewArrayInsn(String desc, int dims) {
    mutate();
  }

  public void visitTryCatchBlock(Label start, Label end, Label handler,
      String type) {
    mutate();
  }
  
  private void mutate() {
    this.context.registerMutation(this.factory, "Null mutation");
  }

}
