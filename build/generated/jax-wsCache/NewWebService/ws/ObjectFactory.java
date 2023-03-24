
package ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Correos_QNAME = new QName("http://ws/", "Correos");
    private final static QName _CorreosResponse_QNAME = new QName("http://ws/", "CorreosResponse");
    private final static QName _Especialidades_QNAME = new QName("http://ws/", "Especialidades");
    private final static QName _EspecialidadesResponse_QNAME = new QName("http://ws/", "EspecialidadesResponse");
    private final static QName _Telefonos_QNAME = new QName("http://ws/", "Telefonos");
    private final static QName _TelefonosResponse_QNAME = new QName("http://ws/", "TelefonosResponse");
    private final static QName _Hello_QNAME = new QName("http://ws/", "hello");
    private final static QName _HelloResponse_QNAME = new QName("http://ws/", "helloResponse");
    private final static QName _InformacionDoctores_QNAME = new QName("http://ws/", "informacionDoctores");
    private final static QName _InformacionDoctoresResponse_QNAME = new QName("http://ws/", "informacionDoctoresResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Correos }
     * 
     */
    public Correos createCorreos() {
        return new Correos();
    }

    /**
     * Create an instance of {@link CorreosResponse }
     * 
     */
    public CorreosResponse createCorreosResponse() {
        return new CorreosResponse();
    }

    /**
     * Create an instance of {@link Especialidades }
     * 
     */
    public Especialidades createEspecialidades() {
        return new Especialidades();
    }

    /**
     * Create an instance of {@link EspecialidadesResponse }
     * 
     */
    public EspecialidadesResponse createEspecialidadesResponse() {
        return new EspecialidadesResponse();
    }

    /**
     * Create an instance of {@link Telefonos }
     * 
     */
    public Telefonos createTelefonos() {
        return new Telefonos();
    }

    /**
     * Create an instance of {@link TelefonosResponse }
     * 
     */
    public TelefonosResponse createTelefonosResponse() {
        return new TelefonosResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link InformacionDoctores }
     * 
     */
    public InformacionDoctores createInformacionDoctores() {
        return new InformacionDoctores();
    }

    /**
     * Create an instance of {@link InformacionDoctoresResponse }
     * 
     */
    public InformacionDoctoresResponse createInformacionDoctoresResponse() {
        return new InformacionDoctoresResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Correos }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Correos }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "Correos")
    public JAXBElement<Correos> createCorreos(Correos value) {
        return new JAXBElement<Correos>(_Correos_QNAME, Correos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CorreosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CorreosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "CorreosResponse")
    public JAXBElement<CorreosResponse> createCorreosResponse(CorreosResponse value) {
        return new JAXBElement<CorreosResponse>(_CorreosResponse_QNAME, CorreosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Especialidades }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Especialidades }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "Especialidades")
    public JAXBElement<Especialidades> createEspecialidades(Especialidades value) {
        return new JAXBElement<Especialidades>(_Especialidades_QNAME, Especialidades.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EspecialidadesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EspecialidadesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "EspecialidadesResponse")
    public JAXBElement<EspecialidadesResponse> createEspecialidadesResponse(EspecialidadesResponse value) {
        return new JAXBElement<EspecialidadesResponse>(_EspecialidadesResponse_QNAME, EspecialidadesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Telefonos }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Telefonos }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "Telefonos")
    public JAXBElement<Telefonos> createTelefonos(Telefonos value) {
        return new JAXBElement<Telefonos>(_Telefonos_QNAME, Telefonos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TelefonosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TelefonosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "TelefonosResponse")
    public JAXBElement<TelefonosResponse> createTelefonosResponse(TelefonosResponse value) {
        return new JAXBElement<TelefonosResponse>(_TelefonosResponse_QNAME, TelefonosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InformacionDoctores }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link InformacionDoctores }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "informacionDoctores")
    public JAXBElement<InformacionDoctores> createInformacionDoctores(InformacionDoctores value) {
        return new JAXBElement<InformacionDoctores>(_InformacionDoctores_QNAME, InformacionDoctores.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InformacionDoctoresResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link InformacionDoctoresResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "informacionDoctoresResponse")
    public JAXBElement<InformacionDoctoresResponse> createInformacionDoctoresResponse(InformacionDoctoresResponse value) {
        return new JAXBElement<InformacionDoctoresResponse>(_InformacionDoctoresResponse_QNAME, InformacionDoctoresResponse.class, null, value);
    }

}
