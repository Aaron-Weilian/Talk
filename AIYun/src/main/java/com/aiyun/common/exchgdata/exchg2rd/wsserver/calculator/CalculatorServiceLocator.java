///**
// * CalculatorServiceLocator.java
// *
// * This file was auto-generated from WSDL
// * by the Apache Axis WSDL2Java emitter.
// */
//
//package com.aiyun.common.exchgdata.exchg2rd.wsserver.calculator;
//
//public class CalculatorServiceLocator extends org.apache.axis.client.Service implements CalculatorService {
//
//    // Use to get a proxy class for Calculator
//    private final java.lang.String Calculator_address = "http://localhost:8083/axis/Calculator.jws";
//
//    public java.lang.String getCalculatorAddress() {
//        return Calculator_address;
//    }
//
//    // The WSDD service name defaults to the port name.
//    private java.lang.String CalculatorWSDDServiceName = "Calculator";
//
//    public java.lang.String getCalculatorWSDDServiceName() {
//        return CalculatorWSDDServiceName;
//    }
//
//    public void setCalculatorWSDDServiceName(java.lang.String name) {
//        CalculatorWSDDServiceName = name;
//    }
//
//    public Calculator getCalculator() throws javax.xml.rpc.ServiceException {
//       java.net.URL endpoint;
//        try {
//            endpoint = new java.net.URL(Calculator_address);
//        }
//        catch (java.net.MalformedURLException e) {
//            throw new javax.xml.rpc.ServiceException(e);
//        }
//        return getCalculator(endpoint);
//    }
//
//    public Calculator getCalculator(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
//        try {
//            CalculatorSoapBindingStub _stub = new CalculatorSoapBindingStub(portAddress, this);
//            _stub.setPortName(getCalculatorWSDDServiceName());
//            return _stub;
//        }
//        catch (org.apache.axis.AxisFault e) {
//            return null;
//        }
//    }
//
//    /**
//     * For the given interface, get the stub implementation.
//     * If this service has no port for the given interface,
//     * then ServiceException is thrown.
//     */
//    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
//        try {
//            if (Calculator.class.isAssignableFrom(serviceEndpointInterface)) {
//                CalculatorSoapBindingStub _stub = new CalculatorSoapBindingStub(new java.net.URL(Calculator_address), this);
//                _stub.setPortName(getCalculatorWSDDServiceName());
//                return _stub;
//            }
//        }
//        catch (java.lang.Throwable t) {
//            throw new javax.xml.rpc.ServiceException(t);
//        }
//        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
//    }
//
//    /**
//     * For the given interface, get the stub implementation.
//     * If this service has no port for the given interface,
//     * then ServiceException is thrown.
//     */
//    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
//        if (portName == null) {
//            return getPort(serviceEndpointInterface);
//        }
//        String inputPortName = portName.getLocalPart();
//        if ("Calculator".equals(inputPortName)) {
//            return getCalculator();
//        }
//        else  {
//            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
//            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
//            return _stub;
//        }
//    }
//
//    public javax.xml.namespace.QName getServiceName() {
//        return new javax.xml.namespace.QName("http://localhost:8083/axis/Calculator.jws", "CalculatorService");
//    }
//
//    private java.util.HashSet ports = null;
//
//    public java.util.Iterator getPorts() {
//        if (ports == null) {
//            ports = new java.util.HashSet();
//            ports.add(new javax.xml.namespace.QName("Calculator"));
//        }
//        return ports.iterator();
//    }
//
//}
