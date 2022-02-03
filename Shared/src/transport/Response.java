package transport;

import reference.ReferenceSystem;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "response")
@XmlRootElement
public class Response implements Serializable {

    ReferenceSystem department;

    public Response() {
    }

    public Response(ReferenceSystem department) {
        this.department = department;
    }

    public void setDepartment(ReferenceSystem department) {
        this.department = department;
    }

    public ReferenceSystem getDepartment() {
        return department;
    }


}
