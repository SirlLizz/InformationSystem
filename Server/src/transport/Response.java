package transport;

import reference.ReferenceSystem;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "response")
@XmlRootElement
public class Response {

    ReferenceSystem department;

    public Response() {
    }

    public void setDepartment(ReferenceSystem department) {
        this.department = department;
    }

    public ReferenceSystem getDepartment() {
        return department;
    }

    public Response(ReferenceSystem department) {
        this.department = department;
    }
}
