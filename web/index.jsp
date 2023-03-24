<%@page import="sistema.modelAdmin.Doctor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

    <title>UAEM</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/carousel/">


    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        .b-example-divider {
            height: 3rem;
            background-color: rgba(0, 0, 0, .1);
            border: solid rgba(0, 0, 0, .15);
            border-width: 1px 0;
            box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
        }

        .b-example-vr {
            flex-shrink: 0;
            width: 1.5rem;
            height: 100vh;
        }

        .bi {
            vertical-align: -.125em;
            fill: currentColor;
        }

        .nav-scroller {
            position: relative;
            z-index: 2;
            height: 2.75rem;
            overflow-y: hidden;
        }

        .nav-scroller .nav {
            display: flex;
            flex-wrap: nowrap;
            padding-bottom: 1rem;
            margin-top: -1px;
            overflow-x: auto;
            text-align: center;
            white-space: nowrap;
            -webkit-overflow-scrolling: touch;
        }
    </style>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/carousel.css" rel="stylesheet">
    <link href="css/carousel.rtl.css" rel="stylesheet">
    <link href="css/estilos.css" rel="stylesheet">
</head>
<body>

    <header>
        <nav class="navbar navbar-expand-md navbar-dark fixed-top " style="background-color: #7db458;>
             <div class="container-fluid">



            <a class="navbar-brand" href="https://www.uaem.mx/">

                <img src="img/logouaem.svg" alt="alt" width="25%" height="25%"/>

            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="https://www.uaem.mx/vida-universitaria/">Vida Universitaria</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="https://www.uaem.mx/admision-y-oferta/">Admision y oferta</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="https://www.uaem.mx/estudiantes-y-egresados/">Estudiantes Egresados</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="https://www.uaem.mx/generacion-de-conocimiento/">Generacion de Conocimiento</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="https://www.uaem.mx/organizacion-institucional/">Organizacion institucional</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="https://www.uaem.mx/difusion-y-medios/">Difusion y Medios</a>
                    </li>


                </ul>


                <ul class="navbar-nav ms-auto mb-2 mb-md-0 d-flex">
                    <li class="nav-item">

                    <li>
                        <a class="nav-link active btn btn-whithe" href="UsersController?action=registrer" >Registrarse</a>
                    </li>

                    <li class="nav-item">

                    <li>
                        <a class="nav-link active btn btn-primary" href="UsersController?action=Login">Iniciar sesion</a>
                    </li>


                </ul>


            </div>
            </div>
        </nav>
    </header>

    <main>
        
        

        
        

        <div id="myCarousel" class="carousel slide" data-bs-ride="carousel">


            <div class="carousel-indicators">
                <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>


            <div class="carousel-inner">
                <div class="carousel-item active" style="background-color: #007bff;" >

                    <img class="bd-placeholder-img" src="img/alm04.png" alt="alt" width="100%" height="100%"/>


                    <div class="container">

                        <div class="carousel-caption text-start">
                            <h1 class="col-4" style="background-color: #17a2b8;">Historia, Academia,<br> Ciencia
                                Cultura, Deporte</h1>

                        </div>
                    </div>



                </div>

                <div class="carousel-item">

                    <img class="bd-placeholder-img" src="img/logo7.jpg" alt="alt" width="100%" height="100%"/>

                    <div class="container">
                        <div class="carousel-caption">
                            <h1>Consultas Para Estudiantes</h1>
                            <p>Agenda una Cita</p>
                            <p><a class="btn btn-lg btn-primary" href="UsersController?action=Login">Agendar</a></p>
                        </div>
                    </div>
                </div>



                <div class="carousel-item">

                    <img class="bd-placeholder-img" src="img/logo6.jpg" alt="alt" width="100%" height="100%"/>

                    <div class="container">
                        <div class="carousel-caption text-end">

                        </div>
                    </div>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>





        <div class="container marketing">
            
            
            


            
           
            <div class="row">
                <div class="col-lg-4">
                     <img src="img/logo11.jpg" alt="" width="150" height="150" class="rounded-circle me-2">

  
                 
                            
                            <h2 class="fw-normal"> </h2>

                            <h2 class="fw-normal"><%
                                try {
                                    ws.NewWebService_Service service = new ws.NewWebService_Service();
                                    ws.NewWebService port = service.getNewWebServicePort();
                                    // TODO process result here
                                    java.util.List<java.lang.String> result = port.informacionDoctores();
                                    out.println(result.get(0));
                                } catch (Exception ex) {
                                    // TODO handle custom exceptions here
                                }
                                %>

                                
                                
                              
            </h2>
                    <p>
    <%
    try {
	ws.NewWebService_Service service = new ws.NewWebService_Service();
	ws.NewWebService port = service.getNewWebServicePort();
	// TODO process result here
	java.util.List<java.lang.String> result = port.especialidades();
	out.println(result.get(0));
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %></p>
                    <p><a class="btn btn-secondary" href="DoctoresServicio.jsp">Ver &raquo;</a></p>

                </div><!-- /.col-lg-4 -->
                
          
                <div class="col-lg-4">
                     <img src="img/logo10.jpg" alt="" width="150" height="150" class="rounded-circle me-2">

                    <h2 class="fw-normal"><%
                                try {
                                    ws.NewWebService_Service service = new ws.NewWebService_Service();
                                    ws.NewWebService port = service.getNewWebServicePort();
                                    // TODO process result here
                                    java.util.List<java.lang.String> result = port.informacionDoctores();
                                    out.println(result.get(1));
                                } catch (Exception ex) {
                                    // TODO handle custom exceptions here
                                }
                                %></h2>
                    <p><%
    try {
	ws.NewWebService_Service service = new ws.NewWebService_Service();
	ws.NewWebService port = service.getNewWebServicePort();
	// TODO process result here
	java.util.List<java.lang.String> result = port.especialidades();
	out.println(result.get(1));
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %></p>
                    <p><a class="btn btn-secondary" href="DoctoresServicio.jsp">Ver &raquo;</a></p>
                </div><!-- /.col-lg-4 -->
                <div class="col-lg-4">
                    <img src="img/logo9.jpg" alt="" width="150" height="150" class="rounded-circle me-2">

                    <h2 class="fw-normal"><%
                                try {
                                    ws.NewWebService_Service service = new ws.NewWebService_Service();
                                    ws.NewWebService port = service.getNewWebServicePort();
                                    // TODO process result here
                                    java.util.List<java.lang.String> result = port.informacionDoctores();
                                    out.println(result.get(2));
                                } catch (Exception ex) {
                                    // TODO handle custom exceptions here
                                }
                                %></h2>
                    <p><%
    try {
	ws.NewWebService_Service service = new ws.NewWebService_Service();
	ws.NewWebService port = service.getNewWebServicePort();
	// TODO process result here
	java.util.List<java.lang.String> result = port.especialidades();
	out.println(result.get(2));
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %></p>
                    <p><a class="btn btn-secondary" href="DoctoresServicio.jsp">Ver &raquo;</a></p>
                </div><!-- /.col-lg-4 -->
            </div><!-- /.row -->


            <!-- START THE FEATURETTES -->

            <hr class="featurette-divider">

            <div class="row featurette">
                <div class="col-md-7">
                    <h2 class="featurette-heading fw-normal lh-1">Agenda una Cita <span class="text-muted"></span></h2>
                    <p class="lead">Uno de nuestros Especialistas te Atendera</p>
                </div>
                <div class="col-md-5">

                    <img class="bd-placeholder-img" src="img/doctor2.jpg" alt="alt" width="500" height="500"/>


                </div>
            </div>



                    


            <hr class="featurette-divider">

            <div class="row featurette">
                <div class="col-md-7 order-md-2">
                    <h2 class="featurette-heading fw-normal lh-1">Verifica  Diagnostico<span class="text-muted"></span></h2>
                    <p class="lead">Verifica Tu Diagnostico Que Te Realizaen Nuestros Especialistas</p>
                </div>
                <div class="col-md-5 order-md-1">

                    <img class="bd-placeholder-img" src="img/doctor3.jpg" alt="alt" width="500" height="500"/>

                </div>
            </div>

            <hr class="featurette-divider">

            <div class="row featurette">
                <div class="col-md-7">
                    <h2 class="featurette-heading fw-normal lh-1">Genera Comprobantes Medicos<span class="text-muted"></span></h2>
                    <p class="lead">Presenta tu Comprobante Medico Para tu Atencion y Correcta Cita</p>
                </div>
                <div class="col-md-5">
                    <img class="bd-placeholder-img" src="img/doctor1.jpg" alt="alt" width="500" height="500"/>
                </div>
            </div>

            <hr class="featurette-divider">

            <!-- /END THE FEATURETTES -->

        </div><!-- /.container -->
        
        
        
        
        
        
        
        
        
        

        
        
        <!-- FOOTER -->
        <footer class="container">
            <p class="float-end"><a href="#">Back to top</a></p>

            <p>Redes sociales <a href="https://www.facebook.com/uaem.mx">Facebook</a> , <a href="https://twitter.com/UAEMorelos">twitter</a>, <a href="https://www.youtube.com/user/UAEMor">Yotube</a>.</p>
        </footer>
    </main>


                    <script src="js/bootstrap.min.js"></script>
                    <script src="js/jquery-3.2.1.min.js"></script>
                    <script src="js/jquery-ui.min.js"></script>
                    <script src="js/popper.min.js"></script><script src="../assets/dist/js/bootstrap.bundle.min.js"></script>



  
</body>
</html>
