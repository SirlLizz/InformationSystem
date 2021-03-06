package com.example.shared.transport;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "request")
@XmlRootElement
public class Request implements Serializable {

    private String command;
    private String[] args;
    private String[][] matrixArgs;

    public Request(){}

    public Request(String command, String[] args) {
        this.command = command;
        this.args = args;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public String[][] getMatrixArgs() {
        return matrixArgs;
    }

    public void setMatrixArgs(String[][] matrixArgs) {
        this.matrixArgs = matrixArgs;
    }
}
