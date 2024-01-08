.class public Output 
.super java/lang/Object

.method public <init>()V
 aload_0
 invokenonvirtual java/lang/Object/<init>()V
 return
.end method

.method public static print(I)V
 .limit stack 2
 getstatic java/lang/System/out Ljava/io/PrintStream;
 iload_0 
 invokestatic java/lang/Integer/toString(I)Ljava/lang/String;
 invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
 return
.end method

.method public static read()I
 .limit stack 3
 new java/util/Scanner
 dup
 getstatic java/lang/System/in Ljava/io/InputStream;
 invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
 invokevirtual java/util/Scanner/next()Ljava/lang/String;
 invokestatic java/lang/Integer.parseInt(Ljava/lang/String;)I
 ireturn
.end method

.method public static run()V
 .limit stack 1024
 .limit locals 256
 ldc 2
 istore 0
 invokestatic Output/read()I
 istore 1
 invokestatic Output/read()I
 istore 2
L0:
 iload 1
 iload 2
 if_icmpeq L1
 iload 1
 iload 2
 if_icmple L3
 iload 1
 iload 2
 isub 
 istore 1
 goto L2
L3:
 iload 2
 iload 1
 isub 
 istore 2
L2:
 goto L0
L1:
 iload 1
 invokestatic Output/print(I)V
 iload 1
 iload 2
 ldc 100
 iadd 
 invokestatic Output/print(I)V
 ldc 2023
 invokestatic Output/print(I)V
 ldc 10
 invokestatic Output/print(I)V
 ldc 20
 invokestatic Output/print(I)V
 ldc 30
 invokestatic Output/print(I)V
 ldc 22
 istore 3
 ldc 22
 istore 4
 iload 3
 invokestatic Output/print(I)V
 iload 4
 invokestatic Output/print(I)V
 return
.end method

.method public static main([Ljava/lang/String;)V
 invokestatic Output/run()V
 return
.end method

