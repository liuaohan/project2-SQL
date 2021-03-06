// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: services.proto

package rpc;

public final class SparkGProto {
  private SparkGProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_rpc_Attribute_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_rpc_Attribute_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_rpc_Node_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_rpc_Node_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_rpc_DAGJob_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_rpc_DAGJob_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_rpc_DAGJobJson_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_rpc_DAGJobJson_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_rpc_JobReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_rpc_JobReply_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_rpc_JobRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_rpc_JobRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_rpc_UBTable_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_rpc_UBTable_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016services.proto\022\003rpc\"6\n\tAttribute\022\033\n\004ty" +
      "pe\030\001 \001(\0162\r.rpc.DataType\022\014\n\004name\030\002 \001(\t\"\205\001" +
      "\n\004Node\022\033\n\004type\030\001 \001(\0162\r.rpc.NodeType\022\014\n\004n" +
      "ame\030\002 \001(\t\022\025\n\rpre_node_list\030\003 \003(\t\022\026\n\016para" +
      "meter_list\030\004 \003(\t\022#\n\013output_list\030\005 \003(\0132\016." +
      "rpc.Attribute\"\252\001\n\006DAGJob\022\014\n\004name\030\001 \001(\t\022 " +
      "\n\rall_node_list\030\002 \003(\0132\t.rpc.Node\022\021\n\tis_s" +
      "tream\030\003 \001(\010\022\017\n\007latency\030\004 \001(\002\022#\n\010platform" +
      "\030\005 \001(\0162\021.rpc.PlatformType\022\026\n\016use_checkpo" +
      "int\030\006 \001(\010\022\017\n\007is_test\030\007 \001(\010\"\032\n\nDAGJobJson" +
      "\022\014\n\004json\030\001 \001(\t\"b\n\010JobReply\022\024\n\014compile_in" +
      "fo\030\001 \001(\t\022\030\n\020compile_err_info\030\002 \001(\t\022\020\n\010ru" +
      "n_info\030\003 \001(\t\022\024\n\014run_err_info\030\004 \001(\t\"\305\001\n\nJ" +
      "obRequest\022\014\n\004code\030\001 \001(\t\022\014\n\004name\030\002 \001(\t\022#\n" +
      "\010language\030\003 \001(\0162\021.rpc.LanguageType\022\021\n\tis" +
      "_stream\030\004 \001(\010\022#\n\010platform\030\005 \001(\0162\021.rpc.Pl" +
      "atformType\022 \n\ninput_list\030\006 \003(\0132\014.rpc.UBT" +
      "able\022\034\n\006output\030\007 \001(\0132\014.rpc.UBTable\"\207\001\n\007U" +
      "BTable\022\014\n\004name\030\001 \001(\t\022\034\n\004type\030\002 \001(\0162\016.rpc" +
      ".TableType\022\013\n\003URL\030\003 \001(\t\022\014\n\004path\030\004 \001(\t\022\r\n" +
      "\005topic\030\005 \001(\t\022\016\n\006schema\030\006 \001(\t\022\026\n\016use_chec" +
      "kpoint\030\007 \001(\010*/\n\014PlatformType\022\t\n\005Local\020\000\022" +
      "\013\n\007Cluster\020\001\022\007\n\003HRM\020\002*\262\001\n\010NodeType\022\n\n\006Re" +
      "ader\020\000\022\013\n\007GroupBy\020\001\022\r\n\tMakeFrame\020\002\022\r\n\tDe" +
      "tection\020\003\022\t\n\005Speed\020\004\022\007\n\003LPR\020\005\022\010\n\004Sort\020\006\022" +
      "\010\n\004Crop\020\007\022\010\n\004Fork\020\010\022\020\n\014Segmentation\020\t\022\022\n" +
      "\016Classification\020\n\022\n\n\006Writer\020\013\022\013\n\007Tracker" +
      "\020\014*9\n\010DataType\022\013\n\007Integer\020\000\022\t\n\005Float\020\001\022\n" +
      "\n\006String\020\002\022\t\n\005Bytes\020\003*8\n\014LanguageType\022\010\n" +
      "\004Java\020\000\022\t\n\005Scala\020\001\022\n\n\006Python\020\002\022\007\n\003SQL\020\003*" +
      " \n\007RunType\022\t\n\005Batch\020\000\022\n\n\006Stream\020\001*-\n\tTab" +
      "leType\022\010\n\004File\020\000\022\t\n\005Kafka\020\001\022\013\n\007Console\020\002" +
      "2\315\001\n\tSparkGAPI\022,\n\014SubmitDAGJob\022\013.rpc.DAG" +
      "Job\032\r.rpc.JobReply\"\000\0226\n\022SubmitDAGJobByJs" +
      "on\022\017.rpc.DAGJobJson\032\r.rpc.JobReply\"\000\022-\n\t" +
      "SubmitJob\022\017.rpc.JobRequest\032\r.rpc.JobRepl" +
      "y\"\000\022+\n\007TestJob\022\017.rpc.JobRequest\032\r.rpc.Jo" +
      "bReply\"\000B\032\n\003rpcB\013SparkGProtoP\001\242\002\003HLWb\006pr" +
      "oto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_rpc_Attribute_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_rpc_Attribute_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_rpc_Attribute_descriptor,
        new java.lang.String[] { "Type", "Name", });
    internal_static_rpc_Node_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_rpc_Node_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_rpc_Node_descriptor,
        new java.lang.String[] { "Type", "Name", "PreNodeList", "ParameterList", "OutputList", });
    internal_static_rpc_DAGJob_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_rpc_DAGJob_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_rpc_DAGJob_descriptor,
        new java.lang.String[] { "Name", "AllNodeList", "IsStream", "Latency", "Platform", "UseCheckpoint", "IsTest", });
    internal_static_rpc_DAGJobJson_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_rpc_DAGJobJson_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_rpc_DAGJobJson_descriptor,
        new java.lang.String[] { "Json", });
    internal_static_rpc_JobReply_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_rpc_JobReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_rpc_JobReply_descriptor,
        new java.lang.String[] { "CompileInfo", "CompileErrInfo", "RunInfo", "RunErrInfo", });
    internal_static_rpc_JobRequest_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_rpc_JobRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_rpc_JobRequest_descriptor,
        new java.lang.String[] { "Code", "Name", "Language", "IsStream", "Platform", "InputList", "Output", });
    internal_static_rpc_UBTable_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_rpc_UBTable_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_rpc_UBTable_descriptor,
        new java.lang.String[] { "Name", "Type", "URL", "Path", "Topic", "Schema", "UseCheckpoint", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
