// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: services.proto

package rpc;

/**
 * <pre>
 * 运行的平台
 * </pre>
 *
 * Protobuf enum {@code rpc.PlatformType}
 */
public enum PlatformType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <pre>
   * 本地Spark
   * </pre>
   *
   * <code>Local = 0;</code>
   */
  Local(0),
  /**
   * <pre>
   * 分布式Spark
   * </pre>
   *
   * <code>Cluster = 1;</code>
   */
  Cluster(1),
  /**
   * <pre>
   * 课题3的调度器
   * </pre>
   *
   * <code>HRM = 2;</code>
   */
  HRM(2),
  UNRECOGNIZED(-1),
  ;

  /**
   * <pre>
   * 本地Spark
   * </pre>
   *
   * <code>Local = 0;</code>
   */
  public static final int Local_VALUE = 0;
  /**
   * <pre>
   * 分布式Spark
   * </pre>
   *
   * <code>Cluster = 1;</code>
   */
  public static final int Cluster_VALUE = 1;
  /**
   * <pre>
   * 课题3的调度器
   * </pre>
   *
   * <code>HRM = 2;</code>
   */
  public static final int HRM_VALUE = 2;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static PlatformType valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static PlatformType forNumber(int value) {
    switch (value) {
      case 0: return Local;
      case 1: return Cluster;
      case 2: return HRM;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<PlatformType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      PlatformType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<PlatformType>() {
          public PlatformType findValueByNumber(int number) {
            return PlatformType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalStateException(
          "Can't get the descriptor of an unrecognized enum value.");
    }
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return rpc.SparkGProto.getDescriptor().getEnumTypes().get(0);
  }

  private static final PlatformType[] VALUES = values();

  public static PlatformType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private PlatformType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:rpc.PlatformType)
}

