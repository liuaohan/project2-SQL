// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: services.proto

package rpc;

public interface AttributeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:rpc.Attribute)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.rpc.DataType type = 1;</code>
   * @return The enum numeric value on the wire for type.
   */
  int getTypeValue();
  /**
   * <code>.rpc.DataType type = 1;</code>
   * @return The type.
   */
  rpc.DataType getType();

  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();
}
