<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hello User!</title>

 <jsp:include page="req_css.jsp" />

</head>
<body class="nav-md">

<div class="container body">
      <div class="main_container">

<jsp:include page="left-bar-user.jsp" />
<jsp:include page="header.jsp" /> 

<!-- page content -->
 <div class="right_col" role="main" style="min-height: 958px;">
          <div>
            <div class="page-title">
              
                <h3>${userBean.fullName}</h3>
               
            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <!-- <div class="x_title">
                    <h2>Plain Page</h2>
                    
                    <div class="clearfix"></div>
                  </div> -->
                  <div class="x_content">
                     
                     
                     <!-- CONTENT GOES HERE... -->
                     
                     
                     <div class="x_content">
                    <div class="col-md-3 col-sm-3 col-xs-12 profile_left">
                      <div class="profile_img">
                        <div id="crop-avatar">
                          <!-- Current avatar -->
                          <!-- <img class="img-responsive avatar-view" src="images/new-user.jpg" alt="Avatar" title="Change the avatar"> -->
                          
                          					<c:choose>

														<c:when test="${userBean.photoPath != null}">
												            <img alt="image not avail.." class="img-responsive" src="images/${userBean.photoPath}">
												         </c:when>

														<c:otherwise>
												            <img alt="image not avail.." class="img-responsive" src="images/new-user.jpg">
												         </c:otherwise>
												</c:choose>
                          
                        </div>
                      </div>
                      <h3>${userBean.fullName}</h3>

                      <ul class="list-unstyled user_data">
                        <li><i class="fa fa-briefcase user-profile-icon"></i> &nbsp; ${userBean.department}
                        </li>

                        <li>
                          <i class="fa fa-paper-plane user-profile-icon"></i> &nbsp; ${userBean.emailId}
                        </li>

                        <li>
                          <i class="fa fa-phone-square user-profile-icon"></i>&nbsp;&nbsp;
                          ${userBean.contactNo}
                        </li>
                      </ul>

                      <a class="btn btn-success" href="editPersonalDetails"><i class="fa fa-edit m-right-xs"></i>Edit Profile</a>
                      <br>

                      

                    </div>
                    <div class="col-md-9 col-sm-9 col-xs-12">

                      <div class="profile_title">
                        <div class="col-md-6">
                          <h2>User Activity Report</h2>
                        </div>
                        <div class="col-md-6">
                          <div id="reportrange" class="pull-right" style="margin-top: 5px; background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #E6E9ED">
                            <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                            <span>August 25, 2019 - September 23, 2019</span> <b class="caret"></b>
                          </div>
                        </div>
                      </div>
                      <!-- start of user-activity-graph -->
                      <div id="graph_bar" style="width: 100%; height: 280px; position: relative;"><svg height="280" version="1.1" width="755" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" style="overflow: hidden; position: relative; left: -0.25px; top: -0.600006px;"><desc>Created with Raphaël @@VERSION</desc><defs></defs><text style="text-anchor: end; font-family: sans-serif; font-size: 12px; font-weight: normal;" x="46.5" y="210.264438096" text-anchor="end" font-family="sans-serif" font-size="12px" stroke="none" fill="#888888" font-weight="normal"><tspan dy="4.000003281546867">0</tspan></text><path style="" fill="none" stroke="#aaaaaa" d="M59,210.264438096H730" stroke-width="0.5"></path><text style="text-anchor: end; font-family: sans-serif; font-size: 12px; font-weight: normal;" x="46.5" y="163.94832857199998" text-anchor="end" font-family="sans-serif" font-size="12px" stroke="none" fill="#888888" font-weight="normal"><tspan dy="3.999994831765605">750</tspan></text><path style="" fill="none" stroke="#aaaaaa" d="M59,163.94832857199998H730" stroke-width="0.5"></path><text style="text-anchor: end; font-family: sans-serif; font-size: 12px; font-weight: normal;" x="46.5" y="117.632219048" text-anchor="end" font-family="sans-serif" font-size="12px" stroke="none" fill="#888888" font-weight="normal"><tspan dy="4.000001640773434">1,500</tspan></text><path style="" fill="none" stroke="#aaaaaa" d="M59,117.632219048H730" stroke-width="0.5"></path><text style="text-anchor: end; font-family: sans-serif; font-size: 12px; font-weight: normal;" x="46.5" y="71.31610952399998" text-anchor="end" font-family="sans-serif" font-size="12px" stroke="none" fill="#888888" font-weight="normal"><tspan dy="4.000000820386703">2,250</tspan></text><path style="" fill="none" stroke="#aaaaaa" d="M59,71.31610952399998H730" stroke-width="0.5"></path><text style="text-anchor: end; font-family: sans-serif; font-size: 12px; font-weight: normal;" x="46.5" y="25" text-anchor="end" font-family="sans-serif" font-size="12px" stroke="none" fill="#888888" font-weight="normal"><tspan dy="4">3,000</tspan></text><path style="" fill="none" stroke="#aaaaaa" d="M59,25H730" stroke-width="0.5"></path><text style="text-anchor: middle; font-family: sans-serif; font-size: 12px; font-weight: normal;" x="696.45" y="222.764438096" text-anchor="middle" font-family="sans-serif" font-size="12px" stroke="none" fill="#888888" font-weight="normal" transform="matrix(0.8192,-0.5736,0.5736,0.8192,-15.2465,455.2387)"><tspan dy="4.000003281546867">Other</tspan></text><text style="text-anchor: middle; font-family: sans-serif; font-size: 12px; font-weight: normal;" x="629.35" y="222.764438096" text-anchor="middle" font-family="sans-serif" font-size="12px" stroke="none" fill="#888888" font-weight="normal" transform="matrix(0.8192,-0.5736,0.5736,0.8192,-49.908,432.525)"><tspan dy="4.000003281546867">iPhone 6S Plus</tspan></text><text style="text-anchor: middle; font-family: sans-serif; font-size: 12px; font-weight: normal;" x="562.25" y="222.764438096" text-anchor="middle" font-family="sans-serif" font-size="12px" stroke="none" fill="#888888" font-weight="normal" transform="matrix(0.8192,-0.5736,0.5736,0.8192,-50.5748,386.008)"><tspan dy="4.000003281546867">iPhone 6S</tspan></text><text style="text-anchor: middle; font-family: sans-serif; font-size: 12px; font-weight: normal;" x="495.15" y="222.764438096" text-anchor="middle" font-family="sans-serif" font-size="12px" stroke="none" fill="#888888" font-weight="normal" transform="matrix(0.8192,-0.5736,0.5736,0.8192,-70.9012,353.2568)"><tspan dy="4.000003281546867">iPhone 6 Plus</tspan></text><text style="text-anchor: middle; font-family: sans-serif; font-size: 12px; font-weight: normal;" x="428.05" y="222.764438096" text-anchor="middle" font-family="sans-serif" font-size="12px" stroke="none" fill="#888888" font-weight="normal" transform="matrix(0.8192,-0.5736,0.5736,0.8192,-71.568,306.7397)"><tspan dy="4.000003281546867">iPhone 6</tspan></text><text style="text-anchor: middle; font-family: sans-serif; font-size: 12px; font-weight: normal;" x="360.95" y="222.764438096" text-anchor="middle" font-family="sans-serif" font-size="12px" stroke="none" fill="#888888" font-weight="normal" transform="matrix(0.8192,-0.5736,0.5736,0.8192,-86.9795,270.5471)"><tspan dy="4.000003281546867">iPhone 5S</tspan></text><text style="text-anchor: middle; font-family: sans-serif; font-size: 12px; font-weight: normal;" x="293.85" y="222.764438096" text-anchor="middle" font-family="sans-serif" font-size="12px" stroke="none" fill="#888888" font-weight="normal" transform="matrix(0.8192,-0.5736,0.5736,0.8192,-95.8378,229.7658)"><tspan dy="4.000003281546867">iPhone 5</tspan></text><text style="text-anchor: middle; font-family: sans-serif; font-size: 12px; font-weight: normal;" x="226.75" y="222.764438096" text-anchor="middle" font-family="sans-serif" font-size="12px" stroke="none" fill="#888888" font-weight="normal" transform="matrix(0.8192,-0.5736,0.5736,0.8192,-114.9355,196.1542)"><tspan dy="4.000003281546867">iPhone 3GS</tspan></text><text style="text-anchor: middle; font-family: sans-serif; font-size: 12px; font-weight: normal;" x="159.65" y="222.764438096" text-anchor="middle" font-family="sans-serif" font-size="12px" stroke="none" fill="#888888" font-weight="normal" transform="matrix(0.8192,-0.5736,0.5736,0.8192,-123.3842,155.0861)"><tspan dy="4.000003281546867">iPhone 4S</tspan></text><text style="text-anchor: middle; font-family: sans-serif; font-size: 12px; font-weight: normal;" x="92.55" y="222.764438096" text-anchor="middle" font-family="sans-serif" font-size="12px" stroke="none" fill="#888888" font-weight="normal" transform="matrix(0.8192,-0.5736,0.5736,0.8192,-132.2425,114.3048)"><tspan dy="4.000003281546867">iPhone 4</tspan></text><rect x="67.3875" y="186.79760927050665" width="50.324999999999996" height="23.466828825493337" rx="0" ry="0" fill="#26b99a" stroke="none" style="fill-opacity: 1;" fill-opacity="1"></rect><rect x="134.48749999999998" y="169.81503577837333" width="50.324999999999996" height="40.449402317626664" rx="0" ry="0" fill="#26b99a" stroke="none" style="fill-opacity: 1;" fill-opacity="1"></rect><rect x="201.58749999999998" y="193.28186460386667" width="50.324999999999996" height="16.982573492133326" rx="0" ry="0" fill="#26b99a" stroke="none" style="fill-opacity: 1;" fill-opacity="1"></rect><rect x="268.68749999999994" y="113.24762734639467" width="50.324999999999996" height="97.01681074960533" rx="0" ry="0" fill="#26b99a" stroke="none" style="fill-opacity: 1;" fill-opacity="1"></rect><rect x="335.78749999999997" y="169.81503577837333" width="50.324999999999996" height="40.449402317626664" rx="0" ry="0" fill="#26b99a" stroke="none" style="fill-opacity: 1;" fill-opacity="1"></rect><rect x="402.8875" y="77.244571543072" width="50.324999999999996" height="133.019866552928" rx="0" ry="0" fill="#26b99a" stroke="none" style="fill-opacity: 1;" fill-opacity="1"></rect><rect x="469.98749999999995" y="139.61693236872532" width="50.324999999999996" height="70.64750572727468" rx="0" ry="0" fill="#26b99a" stroke="none" style="fill-opacity: 1;" fill-opacity="1"></rect><rect x="537.0875" y="63.84377718746134" width="50.324999999999996" height="146.42066090853865" rx="0" ry="0" fill="#26b99a" stroke="none" style="fill-opacity: 1;" fill-opacity="1"></rect><rect x="604.1875" y="119.42310861626133" width="50.324999999999996" height="90.84132947973866" rx="0" ry="0" fill="#26b99a" stroke="none" style="fill-opacity: 1;" fill-opacity="1"></rect><rect x="671.2875" y="125.598589886128" width="50.324999999999996" height="84.665848209872" rx="0" ry="0" fill="#26b99a" stroke="none" style="fill-opacity: 1;" fill-opacity="1"></rect></svg><div class="morris-hover morris-default-style" style="left: 370.05px; top: 110px; display: none;"><div class="morris-hover-row-label">iPhone 6</div><div class="morris-hover-point" style="color: #26B99A">
  Geekbench:
  2,154
</div></div></div>
                      <!-- end of user-activity-graph -->

                      <div class="" role="tabpanel" data-example-id="togglable-tabs">
                        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                          <li role="presentation" class=""><a href="#tab_content1" id="home-tab" role="tab" data-toggle="tab" aria-expanded="false">Recent Activity</a>
                          </li>
                          <li role="presentation" class="active"><a href="#tab_content2" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="true">Projects Worked on</a>
                          </li>
                          <li role="presentation" class=""><a href="#tab_content3" role="tab" id="profile-tab2" data-toggle="tab" aria-expanded="false">Profile</a>
                          </li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                          <div role="tabpanel" class="tab-pane fade" id="tab_content1" aria-labelledby="home-tab">

                            <!-- start recent activity -->
                            <ul class="messages">
                              <li>
                                <img src="images/img.jpg" class="avatar" alt="Avatar">
                                <div class="message_date">
                                  <h3 class="date text-info">24</h3>
                                  <p class="month">May</p>
                                </div>
                                <div class="message_wrapper">
                                  <h4 class="heading">Desmond Davison</h4>
                                  <blockquote class="message">Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua butcher retro keffiyeh dreamcatcher synth.</blockquote>
                                  <br>
                                  <p class="url">
                                    <span class="fs1 text-info" aria-hidden="true" data-icon=""></span>
                                    <a href="#"><i class="fa fa-paperclip"></i> User Acceptance Test.doc </a>
                                  </p>
                                </div>
                              </li>
                              

                            </ul>
                            <!-- end recent activity -->

                          </div>
                          <div role="tabpanel" class="tab-pane fade active in" id="tab_content2" aria-labelledby="profile-tab">

                            <!-- start user projects -->
                            <table class="data table table-striped no-margin">
                              <thead>
                                <tr>
                                  <th>#</th>
                                  <th>Project Name</th>
                                  <th>Client Company</th>
                                  <th class="hidden-phone">Hours Spent</th>
                                  <th>Contribution</th>
                                </tr>
                              </thead>
                              <tbody>
                                <tr>
                                  <td>1</td>
                                  <td>New Company Takeover Review</td>
                                  <td>Deveint Inc</td>
                                  <td class="hidden-phone">18</td>
                                  <td class="vertical-align-mid">
                                    <div class="progress">
                                      <div class="progress-bar progress-bar-success" data-transitiongoal="35" style="width: 35%;" aria-valuenow="35"></div>
                                    </div>
                                  </td>
                                </tr>
                                <tr>
                                  <td>2</td>
                                  <td>New Partner Contracts Consultanci</td>
                                  <td>Deveint Inc</td>
                                  <td class="hidden-phone">13</td>
                                  <td class="vertical-align-mid">
                                    <div class="progress">
                                      <div class="progress-bar progress-bar-danger" data-transitiongoal="15" style="width: 15%;" aria-valuenow="15"></div>
                                    </div>
                                  </td>
                                </tr>
                                <tr>
                                  <td>3</td>
                                  <td>Partners and Inverstors report</td>
                                  <td>Deveint Inc</td>
                                  <td class="hidden-phone">30</td>
                                  <td class="vertical-align-mid">
                                    <div class="progress">
                                      <div class="progress-bar progress-bar-success" data-transitiongoal="45" style="width: 45%;" aria-valuenow="45"></div>
                                    </div>
                                  </td>
                                </tr>
                                <tr>
                                  <td>4</td>
                                  <td>New Company Takeover Review</td>
                                  <td>Deveint Inc</td>
                                  <td class="hidden-phone">28</td>
                                  <td class="vertical-align-mid">
                                    <div class="progress">
                                      <div class="progress-bar progress-bar-success" data-transitiongoal="75" style="width: 75%;" aria-valuenow="75"></div>
                                    </div>
                                  </td>
                                </tr>
                              </tbody>
                            </table>
                            <!-- end user projects -->

                          </div>
                          <div role="tabpanel" class="tab-pane fade" id="tab_content3" aria-labelledby="profile-tab">
                            <p>xxFood truck </p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                     
                     
                     
                     
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div> 

              
            </div>
<!-- /page content -->


</div>
</div>
 <jsp:include page="req_js_jQuery.jsp" />
</body>
</html>