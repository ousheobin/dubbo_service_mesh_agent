package cn.shaobin.agent.rpc;

import java.util.Map;

public final class RpcContentProtos {
  private RpcContentProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface RpcContentOrBuilder extends
      // @@protoc_insertion_point(interface_extends:rpc.RpcContent)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required string interface = 1;</code>
     */
    boolean hasInterface();
    /**
     * <code>required string interface = 1;</code>
     */
    String getInterface();
    /**
     * <code>required string interface = 1;</code>
     */
    com.google.protobuf.ByteString
        getInterfaceBytes();

    /**
     * <code>required string method = 2;</code>
     */
    boolean hasMethod();
    /**
     * <code>required string method = 2;</code>
     */
    String getMethod();
    /**
     * <code>required string method = 2;</code>
     */
    com.google.protobuf.ByteString
        getMethodBytes();

    /**
     * <code>required string parameter = 3;</code>
     */
    boolean hasParameter();
    /**
     * <code>required string parameter = 3;</code>
     */
    String getParameter();
    /**
     * <code>required string parameter = 3;</code>
     */
    com.google.protobuf.ByteString
        getParameterBytes();

    /**
     * <code>required string parameterTypesString = 4;</code>
     */
    boolean hasParameterTypesString();
    /**
     * <code>required string parameterTypesString = 4;</code>
     */
    String getParameterTypesString();
    /**
     * <code>required string parameterTypesString = 4;</code>
     */
    com.google.protobuf.ByteString
        getParameterTypesStringBytes();
  }
  /**
   * Protobuf type {@code rpc.RpcContent}
   */
  public  static final class RpcContent extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:rpc.RpcContent)
      RpcContentOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use RpcContent.newBuilder() to construct.
    private RpcContent(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private RpcContent() {
      interface_ = "";
      method_ = "";
      parameter_ = "";
      parameterTypesString_ = "";
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private RpcContent(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              interface_ = bs;
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              method_ = bs;
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000004;
              parameter_ = bs;
              break;
            }
            case 34: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000008;
              parameterTypesString_ = bs;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return RpcContentProtos.internal_static_rpc_RpcContent_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return RpcContentProtos.internal_static_rpc_RpcContent_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              RpcContent.class, Builder.class);
    }

    private int bitField0_;
    public static final int INTERFACE_FIELD_NUMBER = 1;
    private volatile Object interface_;
    /**
     * <code>required string interface = 1;</code>
     */
    public boolean hasInterface() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string interface = 1;</code>
     */
    public String getInterface() {
      Object ref = interface_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          interface_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string interface = 1;</code>
     */
    public com.google.protobuf.ByteString
        getInterfaceBytes() {
      Object ref = interface_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        interface_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int METHOD_FIELD_NUMBER = 2;
    private volatile Object method_;
    /**
     * <code>required string method = 2;</code>
     */
    public boolean hasMethod() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string method = 2;</code>
     */
    public String getMethod() {
      Object ref = method_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          method_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string method = 2;</code>
     */
    public com.google.protobuf.ByteString
        getMethodBytes() {
      Object ref = method_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        method_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int PARAMETER_FIELD_NUMBER = 3;
    private volatile Object parameter_;
    /**
     * <code>required string parameter = 3;</code>
     */
    public boolean hasParameter() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required string parameter = 3;</code>
     */
    public String getParameter() {
      Object ref = parameter_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          parameter_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string parameter = 3;</code>
     */
    public com.google.protobuf.ByteString
        getParameterBytes() {
      Object ref = parameter_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        parameter_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int PARAMETERTYPESSTRING_FIELD_NUMBER = 4;
    private volatile Object parameterTypesString_;
    /**
     * <code>required string parameterTypesString = 4;</code>
     */
    public boolean hasParameterTypesString() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required string parameterTypesString = 4;</code>
     */
    public String getParameterTypesString() {
      Object ref = parameterTypesString_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          parameterTypesString_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string parameterTypesString = 4;</code>
     */
    public com.google.protobuf.ByteString
        getParameterTypesStringBytes() {
      Object ref = parameterTypesString_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        parameterTypesString_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasInterface()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasMethod()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasParameter()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasParameterTypesString()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, interface_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, method_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, parameter_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, parameterTypesString_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, interface_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, method_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, parameter_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, parameterTypesString_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof RpcContent)) {
        return super.equals(obj);
      }
      RpcContent other = (RpcContent) obj;

      boolean result = true;
      result = result && (hasInterface() == other.hasInterface());
      if (hasInterface()) {
        result = result && getInterface()
            .equals(other.getInterface());
      }
      result = result && (hasMethod() == other.hasMethod());
      if (hasMethod()) {
        result = result && getMethod()
            .equals(other.getMethod());
      }
      result = result && (hasParameter() == other.hasParameter());
      if (hasParameter()) {
        result = result && getParameter()
            .equals(other.getParameter());
      }
      result = result && (hasParameterTypesString() == other.hasParameterTypesString());
      if (hasParameterTypesString()) {
        result = result && getParameterTypesString()
            .equals(other.getParameterTypesString());
      }
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasInterface()) {
        hash = (37 * hash) + INTERFACE_FIELD_NUMBER;
        hash = (53 * hash) + getInterface().hashCode();
      }
      if (hasMethod()) {
        hash = (37 * hash) + METHOD_FIELD_NUMBER;
        hash = (53 * hash) + getMethod().hashCode();
      }
      if (hasParameter()) {
        hash = (37 * hash) + PARAMETER_FIELD_NUMBER;
        hash = (53 * hash) + getParameter().hashCode();
      }
      if (hasParameterTypesString()) {
        hash = (37 * hash) + PARAMETERTYPESSTRING_FIELD_NUMBER;
        hash = (53 * hash) + getParameterTypesString().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static RpcContent parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcContent parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcContent parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcContent parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcContent parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcContent parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcContent parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static RpcContent parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static RpcContent parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static RpcContent parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static RpcContent parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static RpcContent parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(RpcContent prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code rpc.RpcContent}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:rpc.RpcContent)
        RpcContentOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return RpcContentProtos.internal_static_rpc_RpcContent_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return RpcContentProtos.internal_static_rpc_RpcContent_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                RpcContent.class, Builder.class);
      }

      // Construct using cn.shaobin.agent.rpc.RpcContentProtos.RpcContent.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        interface_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        method_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        parameter_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        parameterTypesString_ = "";
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return RpcContentProtos.internal_static_rpc_RpcContent_descriptor;
      }

      public RpcContent getDefaultInstanceForType() {
        return RpcContent.getDefaultInstance();
      }

      public RpcContent build() {
        RpcContent result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public RpcContent buildPartial() {
        RpcContent result = new RpcContent(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.interface_ = interface_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.method_ = method_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.parameter_ = parameter_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.parameterTypesString_ = parameterTypesString_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof RpcContent) {
          return mergeFrom((RpcContent)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(RpcContent other) {
        if (other == RpcContent.getDefaultInstance()) return this;
        if (other.hasInterface()) {
          bitField0_ |= 0x00000001;
          interface_ = other.interface_;
          onChanged();
        }
        if (other.hasMethod()) {
          bitField0_ |= 0x00000002;
          method_ = other.method_;
          onChanged();
        }
        if (other.hasParameter()) {
          bitField0_ |= 0x00000004;
          parameter_ = other.parameter_;
          onChanged();
        }
        if (other.hasParameterTypesString()) {
          bitField0_ |= 0x00000008;
          parameterTypesString_ = other.parameterTypesString_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        if (!hasInterface()) {
          return false;
        }
        if (!hasMethod()) {
          return false;
        }
        if (!hasParameter()) {
          return false;
        }
        if (!hasParameterTypesString()) {
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        RpcContent parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (RpcContent) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private Object interface_ = "";
      /**
       * <code>required string interface = 1;</code>
       */
      public boolean hasInterface() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string interface = 1;</code>
       */
      public String getInterface() {
        Object ref = interface_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            interface_ = s;
          }
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>required string interface = 1;</code>
       */
      public com.google.protobuf.ByteString
          getInterfaceBytes() {
        Object ref = interface_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          interface_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string interface = 1;</code>
       */
      public Builder setInterface(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        interface_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string interface = 1;</code>
       */
      public Builder clearInterface() {
        bitField0_ = (bitField0_ & ~0x00000001);
        interface_ = getDefaultInstance().getInterface();
        onChanged();
        return this;
      }
      /**
       * <code>required string interface = 1;</code>
       */
      public Builder setInterfaceBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        interface_ = value;
        onChanged();
        return this;
      }

      private Object method_ = "";
      /**
       * <code>required string method = 2;</code>
       */
      public boolean hasMethod() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string method = 2;</code>
       */
      public String getMethod() {
        Object ref = method_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            method_ = s;
          }
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>required string method = 2;</code>
       */
      public com.google.protobuf.ByteString
          getMethodBytes() {
        Object ref = method_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          method_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string method = 2;</code>
       */
      public Builder setMethod(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        method_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string method = 2;</code>
       */
      public Builder clearMethod() {
        bitField0_ = (bitField0_ & ~0x00000002);
        method_ = getDefaultInstance().getMethod();
        onChanged();
        return this;
      }
      /**
       * <code>required string method = 2;</code>
       */
      public Builder setMethodBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        method_ = value;
        onChanged();
        return this;
      }

      private Object parameter_ = "";
      /**
       * <code>required string parameter = 3;</code>
       */
      public boolean hasParameter() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required string parameter = 3;</code>
       */
      public String getParameter() {
        Object ref = parameter_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            parameter_ = s;
          }
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>required string parameter = 3;</code>
       */
      public com.google.protobuf.ByteString
          getParameterBytes() {
        Object ref = parameter_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          parameter_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string parameter = 3;</code>
       */
      public Builder setParameter(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        parameter_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string parameter = 3;</code>
       */
      public Builder clearParameter() {
        bitField0_ = (bitField0_ & ~0x00000004);
        parameter_ = getDefaultInstance().getParameter();
        onChanged();
        return this;
      }
      /**
       * <code>required string parameter = 3;</code>
       */
      public Builder setParameterBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        parameter_ = value;
        onChanged();
        return this;
      }

      private Object parameterTypesString_ = "";
      /**
       * <code>required string parameterTypesString = 4;</code>
       */
      public boolean hasParameterTypesString() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>required string parameterTypesString = 4;</code>
       */
      public String getParameterTypesString() {
        Object ref = parameterTypesString_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            parameterTypesString_ = s;
          }
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>required string parameterTypesString = 4;</code>
       */
      public com.google.protobuf.ByteString
          getParameterTypesStringBytes() {
        Object ref = parameterTypesString_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          parameterTypesString_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string parameterTypesString = 4;</code>
       */
      public Builder setParameterTypesString(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        parameterTypesString_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string parameterTypesString = 4;</code>
       */
      public Builder clearParameterTypesString() {
        bitField0_ = (bitField0_ & ~0x00000008);
        parameterTypesString_ = getDefaultInstance().getParameterTypesString();
        onChanged();
        return this;
      }
      /**
       * <code>required string parameterTypesString = 4;</code>
       */
      public Builder setParameterTypesStringBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        parameterTypesString_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:rpc.RpcContent)
    }

    // @@protoc_insertion_point(class_scope:rpc.RpcContent)
    private static final RpcContent DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new RpcContent();
    }

    public static RpcContent getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @Deprecated public static final com.google.protobuf.Parser<RpcContent>
        PARSER = new com.google.protobuf.AbstractParser<RpcContent>() {
      public RpcContent parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new RpcContent(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<RpcContent> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<RpcContent> getParserForType() {
      return PARSER;
    }

    public RpcContent getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_rpc_RpcContent_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_rpc_RpcContent_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\016RPCEvent.proto\022\003rpc\"`\n\nRpcContent\022\021\n\ti" +
      "nterface\030\001 \002(\t\022\016\n\006method\030\002 \002(\t\022\021\n\tparame" +
      "ter\030\003 \002(\t\022\034\n\024parameterTypesString\030\004 \002(\tB" +
      "(\n\024cn.shaobin.agent.rpcB\020RpcContentProto" +
      "s"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_rpc_RpcContent_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_rpc_RpcContent_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_rpc_RpcContent_descriptor,
        new String[] { "Interface", "Method", "Parameter", "ParameterTypesString", });
  }

  public static RpcContent getContent(Map<String,Object> map){
    RpcContent.Builder builder = RpcContent.newBuilder();
    builder.setParameterTypesString((String)map.get("parameterTypesString"));
    builder.setParameter((String)map.get("parameter"));
    builder.setInterface((String)map.get("interface"));
    builder.setMethod((String)map.get("method"));
    return builder.build();
  }
  // @@protoc_insertion_point(outer_class_scope)
}
