module Shared {
    requires java.xml.bind;
    
    exports model;
    opens model to java.xml.bind;
    exports reference;
    exports transport;
}