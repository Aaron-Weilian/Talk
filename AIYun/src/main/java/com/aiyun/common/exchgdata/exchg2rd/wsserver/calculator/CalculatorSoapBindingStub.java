///**
// * CalculatorSoapBindingStub.java
// *
// * This file was auto-generated from WSDL
// * by the Apache Axis WSDL2Java emitter.
// */
//
//package com.aiyun.common.exchgdata.exchg2rd.wsserver.calculator;
//
//public class CalculatorSoapBindingStub extends org.apache.axis.client.Stub implements Calculator {
//    private java.util.Vector cachedSerClasses = new java.util.Vector();
//    private java.util.Vector cachedSerQNames = new java.util.Vector();
//    private java.util.Vector cachedSerFactories = new java.util.Vector();
//    private java.util.Vector cachedDeserFactories = new java.util.Vector();
//
//    static org.apache.axis.description.OperationDesc [] _operations;
//
//    static {
//        _operations = new org.apache.axis.description.OperationDesc[2];
//        org.apache.axis.description.OperationDesc oper;
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("add");
//        oper.addParameter(new javax.xml.namespace.QName("", "i1"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, org.apache.axis.description.ParameterDesc.IN, false, false);
//        oper.addParameter(new javax.xml.namespace.QName("", "i2"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, org.apache.axis.description.ParameterDesc.IN, false, false);
//        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
//        oper.setReturnClass(int.class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "addReturn"));
//        oper.setStyle(org.apache.axis.enum.Style.RPC);
//        oper.setUse(org.apache.axis.enum.Use.ENCODED);
//        _operations[0] = oper;
//
//        oper = new org.apache.axis.description.OperationDesc();
//        oper.setName("subtract");
//        oper.addParameter(new javax.xml.namespace.QName("", "i1"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, org.apache.axis.description.ParameterDesc.IN, false, false);
//        oper.addParameter(new javax.xml.namespace.QName("", "i2"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, org.apache.axis.description.ParameterDesc.IN, false, false);
//        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
//        oper.setReturnClass(int.class);
//        oper.setReturnQName(new javax.xml.namespace.QName("", "subtractReturn"));
//        oper.setStyle(org.apache.axis.enum.Style.RPC);
//        oper.setUse(org.apache.axis.enum.Use.ENCODED);
//        _operations[1] = oper;
//
//    }
//
//    public CalculatorSoapBindingStub() throws org.apache.axis.AxisFault {
//         this(null);
//    }
//
//    public CalculatorSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
//         this(service);
//         super.cachedEndpoint = endpointURL;
//    }
//
//    public CalculatorSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
//        if (service == null) {
//            super.service = new org.apache.axis.client.Service();
//        } else {
//            super.service = service;
//        }
//    }
//
//    private org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
//        try {
//            org.apache.axis.client.Call _call =
//                    (org.apache.axis.client.Call) super.service.createCall();
//            if (super.maintainSessionSet) {
//                _call.setMaintainSession(super.maintainSession);
//            }
//            if (super.cachedUsername != null) {
//                _call.setUsername(super.cachedUsername);
//            }
//            if (super.cachedPassword != null) {
//                _call.setPassword(super.cachedPassword);
//            }
//            if (super.cachedEndpoint != null) {
//                _call.setTargetEndpointAddress(super.cachedEndpoint);
//            }
//            if (super.cachedTimeout != null) {
//                _call.setTimeout(super.cachedTimeout);
//            }
//            if (super.cachedPortName != null) {
//                _call.setPortName(super.cachedPortName);
//            }
//            java.util.Enumeration keys = super.cachedProperties.keys();
//            while (keys.hasMoreElements()) {
//                java.lang.String key = (java.lang.String) keys.nextElement();
//                _call.setProperty(key, super.cachedProperties.get(key));
//            }
//            return _call;
//        }
//        catch (java.lang.Throwable t) {
//            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", t);
//        }
//    }
//
//    public int add(int i1, int i2) throws java.rmi.RemoteException {
//        if (super.cachedEndpoint == null) {
//            throw new org.apache.axis.NoEndPointException();
//        }
//        org.apache.axis.client.Call _call = createCall();
//        _call.setOperation(_operations[0]);
//        _call.setUseSOAPAction(true);
//        _call.setSOAPActionURI("");
//        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
//        _call.setOperationName(new javax.xml.namespace.QName("http://localhost:8083/axis/Calculator.jws", "add"));
//
//        setRequestHeaders(_call);
//        setAttachments(_call);
//        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(i1), new java.lang.Integer(i2)});
//
//        if (_resp instanceof java.rmi.RemoteException) {
//            throw (java.rmi.RemoteException)_resp;
//        }
//        else {
//            getResponseHeaders(_call);
//            extractAttachments(_call);
//            try {
//                return ((java.lang.Integer) _resp).intValue();
//            } catch (java.lang.Exception _exception) {
//                return ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_resp, int.class)).intValue();
//            }
//        }
//    }
//
//    public int subtract(int i1, int i2) throws java.rmi.RemoteException {
//        if (super.cachedEndpoint == null) {
//            throw new org.apache.axis.NoEndPointException();
//        }
//        org.apache.axis.client.Call _call = createCall();
//        _call.setOperation(_operations[1]);
//        _call.setUseSOAPAction(true);
//        _call.setSOAPActionURI("");
//        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
//        _call.setOperationName(new javax.xml.namespace.QName("http://localhost:8083/axis/Calculator.jws", "subtract"));
//
//        setRequestHeaders(_call);
//        setAttachments(_call);
//        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(i1), new java.lang.Integer(i2)});
//
//        if (_resp instanceof java.rmi.RemoteException) {
//            throw (java.rmi.RemoteException)_resp;
//        }
//        else {
//            getResponseHeaders(_call);
//            extractAttachments(_call);
//            try {
//                return ((java.lang.Integer) _resp).intValue();
//            } catch (java.lang.Exception _exception) {
//                return ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_resp, int.class)).intValue();
//            }
//        }
//    }
//
//}
