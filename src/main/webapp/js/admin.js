/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function()
    {
//        alert("OKxxx");
        
        //Update Bagins
        
        function modifyStatus(urlString, elt)
        {
            var nextElt = elt.next();
            var id = elt.attr("title");
            var status = elt.next().val();
           
            status==="0"?status="1":status="0";
            
            $.ajax(
          {
              url: urlString,
              type: "GET",
              data: {id: id,
                    status: status},
              success: function (data) {
                if($(data).find("#response").html()=="success")
                {
                    if($(data).find("#status").html()=="1")
                    {
//                        elt.removeClass("del");
//                        elt.addClass("ok");
                        elt.text("Active");
                        elt.removeClass("btn-danger");
                        elt.addClass("btn-success");
                        nextElt.val("1");
                    }
                    else if($(data).find("#status").html()=="0")
                    {
//                        elt.removeClass("ok");
//                        elt.addClass("del");
                        elt.text("Inactive");
                        elt.removeClass("btn-success");
                        elt.addClass("btn-danger");
                        nextElt.val("0");
                    }
                    
                }
                else
                {
                    alert("Not updated");
                }

                  
            }

          });
        }
        
        $('#cont_type').change(function()
        {
              
              
              var cont = $("#cont_type option:selected").text().toLowerCase();
              
              
              
            var elt = $(this);

            $.ajax(
          {
              url: "ajax_get_cont_cat.htm",
              type: "GET",
              data: {content_type_id: elt.val()
                    },
              success: function (data) {

                var reply = $(data).find("#cont_cat");
                
                var lis = reply.find('li');
                $('#cont_cat').html("");
                
                if(lis.length!=0)
                {
                    $('#cont_cat').html("<option  disabled selected value=\"\">Select Category</option>");
                    for(var i=0; i<lis.length; ++i)
                    {
                        $('#cont_cat').append("<option value=\"" + lis[i].title + "\">" + lis[i].innerHTML + "</option>");
                    }
                }
                else
                {
                    $('#cont_cat').html("<option  disabled selected value=\"\">No Category</option>");
                }
                


                  
            }

          });
          
          
          selectSize(cont);
          
            return false;
        });
        
        function selectSize(contType)
        {
            ////        alert('ok');
//        
//        var contType = $(this).val();
        
        
        
//        alert(elt.value);
//        var contType = elt.val().toLowerCase();
        if(contType=="video")
        {
            $('#sm_th').text("(268x197)");
            $('#md_th').text("(268x398)");
            $('#lg_th').text("(870x570)");
        }
        else if(contType=="ebook")
        {
            $('#sm_th').text("(400x565)");
            $('#md_th').text("(400x565)");
            $('#lg_th').text("(400x565)");
        }
        else
        {
            $('#sm_th').text("(200x200)");
            $('#md_th').text("(420x240)");
            $('#lg_th').text("(640x480)");
        }
        }
        
        $('#cont_cat').change(function()
        {
            
            
            var elt = $(this);

            $.ajax(
          {
              url: "ajax_get_cont_sub_cat.htm",
              type: "GET",
              data: {content_cat_id: elt.val()
                    },
              success: function (data) {

                var reply = $(data).find("#cont_cat");
                
                var lis = reply.find('li');
                $('.cont_sub_cat').html("");
//                $('.multi_sub').html("");
                
                if(lis.length!=0)
                {
                    $('.cont_sub_cat').html("<option  disabled selected value=\"\">Select Sub-Category</option>");
                    for(var i=0; i<lis.length; ++i)
                    {
                        $('.cont_sub_cat').append("<option value=\"" + lis[i].title + "\">" + lis[i].innerHTML + "</option>");
//                        $('.multi_sub').append("<option value=\"" + lis[i].title + "\">" + lis[i].innerHTML + "</option>");
                    }
                    
                    $('.cont_sub_cat').attr('required', 'required');
                    
                }
                else
                {
                    $('.cont_sub_cat').html("<option  selected value=\"0\">No Sub-Category</option>");
//                    $('.multi_sub').html("<option  selected value=\"0\">No Sub-Category</option>");
                    $('.cont_sub_cat').removeAttr('required');
                }
                


                  
            }

          });
            return false;
            
        });
        
        
        
        
        $('.edit_category_master_status').click(function()
        {
//            alert('lkj');
            modifyStatus("edit_category_master_status.htm", $(this));
            
            return false;
        });
        
        $('.edit_user_master_status').click(function()
        {

            

            modifyStatus("edit_user_master_status.htm", $(this));
            
            return false;

//            var elt = $(this);
//            var nextElt = $(this).next();
//            var userId = $(this).attr("title");
//            var status = $(this).next().val();
//
//            status==="0"?status="1":status="0";
//
//            $.ajax(
//          {
//              url: "edit_user_master_status.htm",
//              type: "GET",
//              data: {user_id: userId,
//                    status: status},
//              success: function (data) {
//
//                if($(data).find("#response").html()=="success")
//                {
//                    if($(data).find("#status").html()=="1")
//                    {
//                        elt.removeClass("del");
//                        elt.addClass("ok");
//                        nextElt.val("1");
//                    }
//                    else if($(data).find("#status").html()=="0")
//                    {
//                        elt.removeClass("ok");
//                        elt.addClass("del");
//                        nextElt.val("0");
//                    }
//                }
//                else
//                {
//                    alert("Not updated");
//                }
//
//                  
//            }
//
//          });
//            return false;
        });
        
        $('.edit_sub_category_master_status').click(function()
        {
//            alert('sub');
            modifyStatus("edit_sub_category_master_status.htm", $(this));
            
            return false;
        });
        
        $('#store_type').change(function()
        {
            
            
            var type = $(this).val();
            if(type=="REMOTE")
            {
                $('.url_text').removeAttr('style');
                $('.text').attr('required', 'required');
                
                $('.load').removeAttr('required');
                $('.file_load').attr('style', 'display: none');
                
//                $('#add_content_master_frm').attr('action', 'add_content_master_url?user_id=1');
            }
            else
            {
                $('.file_load').removeAttr('style');
                $('.load').attr('required', 'required');
                
                $('.text').removeAttr('required');
                $('.url_text').attr('style', 'display: none');
                
//                $('#add_content_master_frm').attr('action', 'add_content_master?user_id=1');
            }
            
        });
        
        $('.edit_content_file_status').click(function()
    {
        alert('lkoi');
//        modifyStatus("edit_content_file_status.htm", $(this));
        var urlString = "edit_content_file_status.htm";
        var elt = $(this);
        var nextElt = elt.next();
            var id = elt.attr("title");
            var status = elt.next().val();
           
            status==="0"?status="1":status="0";
//            alert(status);
            $.ajax(
          {
              url: urlString,
              type: "GET",
              data: {id: id,
                    status: status},
              success: function (data) {
                if($(data).find("#response").html()=="success")
                {
//                    if($(data).find("#status").html()=="1")
//                    {
////                        elt.removeClass("del");
////                        elt.addClass("ok");
//                        elt.text("Active");
//                        elt.removeClass("btn-danger");
//                        elt.addClass("btn-success");
//                        nextElt.val("1");
//                    }
                    if($(data).find("#status").html()=="0")
                    {
                        elt.text("Inactive");
                        elt.removeClass("btn-success");
                        elt.addClass("btn-danger");
                        elt.val("Deleted");
                        elt.attr('disabled', 'disabled');
                        nextElt.val("0");
                        alert('done...');
                    }
                    
                }
                else
                {
                    alert("Not updated");
                }

                  
            }

          });
        

//        return false;
    });   
        
        $('.edit_metadata_detail_status').click(function()
        {
            alert('lkj');
//            modifyStatus("edit_category_master_status.htm", $(this));
            
            var urlString = "edit_metadata_detail_status.htm";
        var elt = $(this);
        var nextElt = elt.next();
            var id = elt.attr("title");
            var status = elt.next().val();
           
            status==="0"?status="1":status="0";
//            alert(status);
            $.ajax(
          {
              url: urlString,
              type: "GET",
              data: {id: id,
                    status: status},
              success: function (data) {
                if($(data).find("#response").html()=="success")
                {
                    if($(data).find("#status").html()=="0")
                    {
                        elt.text("Inactive");
                        elt.removeClass("btn-success");
                        elt.addClass("btn-danger");
                        elt.val("Deleted");
                        elt.attr('disabled', 'disabled');
                        nextElt.val("0");
                        alert('done...');
                    }
                    
                }
                else
                {
                    alert("Not updated");
                }

                  
            }

          });
            
            
//            return false;
        });
        
        
        $('.sch_by').change(function(){
        
//        alert('okd');
//            var typeVal = $(this).val();
            $('#load_by_title_frm').submit();
    
        
    });
    

        
//     $('.review_ok, .review_del').click(function()
    $('table').delegate('.review_ok, .review_del', 'click', function()
    {
        
            $('#rmk_area').val('');
            $('#hide_rmk').val($(this).attr('id'));
            
            $('#m_modal_content_file2').modal('show');

//        return false;
    });
    
    
    
    $('#proceed_btn').click(function()
        {
            

            var selectId = $('#hide_rmk').val();
            
            updateStatus($('#'+selectId+''), $('#rmk_area').val());
            
            
        });
        
        function updateStatus(elt, rmk)
        {

            
//            var statusElt = $(this);
           var statusElt = elt;

           var okTag = statusElt.parent().find('.review_ok');
           var delTag = statusElt.parent().find('.review_del');
           var spanTag = statusElt.parent().find('span');
           
//           var statusTag = statusElt.parent().find('button');
           var statusTag = statusElt.parent().parent().prev().find('button');
           

           //Focus
            var statusVal = elt.attr('title');
            statusVal = statusVal==="Approve" ? "3" : "2";
            
            var id = elt.next().val();

            var remarks = rmk;
//            alert('hmmmm');
            $.ajax(
              {
                  url: "review_option.htm",
                  type: "GET",
                  data: {status: statusVal,
                         id: id,
                         remarks: remarks},
                  success: function (data) {
                      

                    if($(data).find("#response").html()=="success")
                    {
                        if($(data).find("#status").html()=="1")
                        {
//                            anchor.removeClass("enable");
//                            anchor.removeClass("del");
//                            anchor.addClass("ok");
//                            nextElt.val("1");
                        }
                        else if($(data).find("#status").html()=="2")
                        {

                            okTag.attr('style', 'display : none');
                            spanTag.attr('style', 'display : none');
//                            delTag.attr('style', 'display : none');

                            delTag.attr('title', 'Disapproved');
                            delTag.removeClass('review_del');

//                            statusTag.attr('style', 'display : block');
                            statusTag.attr('class', '');
                            
                            
                            statusTag.addClass('btn-status btn-danger');
                            statusTag.text('Disapproved');
                        }
                        else if($(data).find("#status").html()=="3")
                        {

//                            okTag.attr('style', 'display : none');
                            delTag.attr('style', 'display : none');
                            spanTag.attr('style', 'display : none');

                            okTag.attr('title', 'Approved');
                            okTag.removeClass('review_ok');

//                            statusTag.attr('style', 'display : block');
                            statusTag.attr('class', '');
                            
                            
                            statusTag.addClass('btn-status btn-success');
                            statusTag.text('Approved');
                            
                            
                            
                            
                        }
                        else if($(data).find("#status").html()=="0")
                        {
//                            anchor.removeClass("ok");
//                            anchor.addClass("del");
//                            nextElt.val("0");
                        }
                    }
                    
                    $('#m_modal_content_file2').modal('hide');
                }

              });
        }
        
        
        $('#cont_btn').click(function(){
            
//            $('#m_modal_content_file').showDialog();
            
//            var elt = $(this);
            var tdElt = $('tbody').find('td');
//            alert(tdElt.length);
            if(tdElt.length==0)
            {
//                alert("0-000");
                $('#msg_dia').text("Please Upload File");
                $('#save_cont').attr("style", "display:none");
                $('#m_modal_content_file').modal('show');
            }
            else
            {
//                alert("1-111");
                $('#msg_dia').text("Are you sure you want to continue?");
                $('#save_cont').attr("style", "display:block");
                $('#m_modal_content_file').modal('show');
                
            }
            
            
            
            return true;
            
        });
        
        
        $('#more_btn').click(function(){
            
//            $('#m_modal_content_file').showDialog();
            
//            var elt = $(this);
            var tdElt = $('tbody').find('td');
//            alert(tdElt.length);
            if(tdElt.length==0)
            {
//                alert("0-000");
                $('#msg_dia').text("Add Metadata");
                $('#m_modal_content_file').modal('show');
                return false;
            }
            
            return true;
            
        });
        
        
        $('table').delegate('.view_rmk', 'click', function()
        {
            var remarkes = $(this).next().val();
            $('#desc_msg').text(remarkes);
            $('#headerTitle').text('Remark!');
            $('#desc_diag').modal('show');
            
            
//            $('#rmk_area').val(remarkes);
//            $('#proceed_btn').hide();
//            $('#rmk_area').attr('disabled', 'disabled');
//            var modal = document.getElementById('myModal');
//            modal.style.display = "block";
            
            
            
            return false;
            
            
            
//            var hideVal = $(this).next().val();
//            $('#desc_msg').text(hideVal);
//            $('#desc_diag').modal('show');
        });
        
        $('#title_size').keyup(function()
        {
            var txtSz = $(this).val();
            var newSz = 50-txtSz.length;
            $('#tx').text(newSz);
        });
        
//        $('.edit_portal_cat_map_status').click(function()
        
        $('table').delegate('.edit_portal_cat_map_status', 'click', function()
        {
  
            
            var elt = $(this);//needed
            var contentPortalId = $('#cont_portal').val();//needed
            var nextElt = elt.next();
            var categoryId = elt.attr("title");//needed
            var catPortalMapStatus = elt.next().val();//needed
           
            catPortalMapStatus==="0"?catPortalMapStatus="1":catPortalMapStatus="0";
//            alert(status);

           

            $.ajax(
          {
              url: "edit_portal_cat_map_status.htm",
              type: "GET",
              data: {categoryId: categoryId,
                     contentPortalId: contentPortalId,
                    catPortalMapStatus: catPortalMapStatus},
              success: function (data) {
                if($(data).find("#response").html()=="success")
                {
                    if($(data).find("#status").html()=="1")
                    {

//                        elt.attr("checked", "checked");

                        elt.text("Enabled");
                        elt.removeClass("btn-danger");
                        elt.addClass("btn-success");
                        nextElt.val("1");
                    }
                    else if($(data).find("#status").html()=="0")
                    {

//                        elt.removeAttr("checked");
                        elt.text("Disabled");
                        elt.removeClass("btn-success");
                        elt.addClass("btn-danger");
                        nextElt.val("0");
                    }
                    
                }
                else
                {
                    alert("Not updated");
                }

                  
            }

          });
            
//            
            return false;
        });
        
        $('.edit_portal_type_map_status').click(function()
        {
            
//            modifyStatus("edit_portal_cat_map_status.htm", $(this));
            var elt = $(this);//needed
            var contPortalId = $('#cont_portal').val();//needed
            var nextElt = elt.next();
            var typeId = elt.attr("title");//needed
            var typePortalMapStatus = elt.next().val();//needed
           
            typePortalMapStatus==="0"?typePortalMapStatus="1":typePortalMapStatus="0";
//            alert(status);
            $.ajax(
          {
              url: "edit_portal_type_map_status.htm",
              type: "GET",
              data: {typeId: typeId,
                     contPortalId: contPortalId,
                    typePortalMapStatus: typePortalMapStatus},
              success: function (data) {
                if($(data).find("#response").html()=="success")
                {
                    if($(data).find("#status").html()=="1")
                    {

//                        elt.attr("checked", "checked");

                        elt.text("Enabled");
                        elt.removeClass("btn-danger");
                        elt.addClass("btn-success");
                        nextElt.val("1");
                    }
                    else if($(data).find("#status").html()=="0")
                    {

//                        elt.removeAttr("checked");
                        elt.text("Disabled");
                        elt.removeClass("btn-success");
                        elt.addClass("btn-danger");
                        nextElt.val("0");
                    }
                    
                }
                else
                {
                    alert("Not updated");
                }

                  
            }

          });
            
//            
            return false;
        });
        
        $('#cont_portal').change(function()
        {
            $('#table_cont').attr('style', 'display: none');
        });
        
        
        $('.other_select').change(function()
        {
            $('#table_cont').attr('style', 'display: none');
        });
        
        $('table').delegate('.edit_portal_cont_map_status', 'click', function()
        {
//            modifyStatus("edit_portal_cat_map_status.htm", $(this));

            

            var elt = $(this);//needed
            var contentPortalId = $('#cont_portal').val();//needed
            var nextElt = elt.next();
            var contentMasterId = elt.attr("title");//needed
            var contPortalMapStatus = elt.next().val();//needed
           
            contPortalMapStatus==="0"?contPortalMapStatus="1":contPortalMapStatus="0";
//            alert(status);
            $.ajax(
          {
              url: "edit_portal_cont_map_status.htm",
              type: "GET",
              data: {contentMasterId: contentMasterId,
                     contentPortalId: contentPortalId,
                    contentPortalMapStatus: contPortalMapStatus},
              success: function (data) {
                if($(data).find("#response").html()=="success")
                {
                    if($(data).find("#status").html()=="1")
                    {

//                        elt.attr("checked", "checked");

                        elt.text("Enabled");
                        elt.removeClass("btn-danger");
                        elt.addClass("btn-success");
                        nextElt.val("1");
                    }
                    else if($(data).find("#status").html()=="0")
                    {

//                        elt.removeAttr("checked");
                        elt.text("Disabled");
                        elt.removeClass("btn-success");
                        elt.addClass("btn-danger");
                        nextElt.val("0");
                    }
                    
                }
                else
                {
                    alert("Not updated");
                }

                  
            }

          });
            
//            
            return false;
        });
        
        
//        $('.dsp_desc').click(function()
        $('table').delegate('.dsp_desc', 'click', function()
        {
            var hideVal = $(this).next().val();
            $('#desc_msg').text(hideVal);
            $('#headerTitle').text('Full Description!!!');
            $('#desc_diag').modal('show');
        });
        
        $('#title_size').keyup(function()
        {
            var txtSz = $(this).val();
            var newSz = 30-txtSz.length;
            $('#tx').text(newSz);
        });
        
        $('#desc_size').keyup(function()
        {
            var txtSz = $(this).val();
            var newSz = 512-txtSz.length;
            $('#dx').text(newSz);
        });
        
        
        $('#cont_visible').change(function()
        {
            var portalOpt = $(this).val();
            if(portalOpt=='CMS')
            {
                $('.option_portal').attr('disabled', 'disabled');
            }
            else
            {
                $('.option_portal').removeAttr('disabled');
            }
            
        });
        
        $('#sub_faq').click(function()
        {
            
            alert('l22spp');
            
            var qst = $('#exampleInputEmail1').val().trim();
            var ans = $('#ppttt').text().trim();
            alert("ans: "+ans)
            if(qst=="" || ans == "")
            {
                if(qst=="")
                {
                    $('#exampleInputEmail1').val("");
                }
                if(ans=="")
                {
                    $('#ppttt').text("");
                }
                alert("Fill The question and answer");
                return false;
            }
            else
            {
                $('#ans_id').val(ans);
                alert(ans);
            }
            return true;
            
        });
        
        $('#sub_faq_edit').click(function()
        {
            
            alert('lqqspp');
            
            var qst = $('#exampleInputEmail1').val().trim();
            var ans = $('#ppttt').text().trim();
            alert(qst);
            alert("Answer: " + ans);
            if(qst=="" || ans == "")
            {
                if(qst=="")
                {
                    $('#exampleInputEmail1').val("");
                }
                if(ans=="")
                {
                    $('#ppttt').text("");
                }
                alert("Fill The question and answer");
                return false;
            }
            else
            {
                $('#ans_id').val(ans);
                alert(ans);
            }
            return true;
            
        });
        
        
        $('.portal_option').change(function()
        {
            
            var eltTitle = $(this).attr("title");
            
            var portalId = $(this).val();
            if(eltTitle != "active")
            {
            $.ajax(
          {
              url: "ajax_get_feature_text.htm",
              type: "GET",
              data: {portal_id: portalId,
                     },
              success: function (data) {
                  
                  $('#replace').html($(data).filter("#replace").html());
                  
            }

          });
      }
      else
      {
          var subId = $('#sub_id').val();
          $.ajax(
          {
              url: "ajax_get_feature_text2.htm",
              type: "GET",
              data: {portal_id: portalId,
                    sub_id: subId,
                     },
              success: function (data) {
                  
                  $('#replace').html($(data).filter("#replace").html());
                  
            }

          });
      }
            
        });
        
        //Update Ends
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        $("#cont_type").change(function()
//        {
//        
////        alert('ok');
//        
//        var contType = $(this).val();
//        if(contType=="VIDEO")
//        {
//            $('#sm_th').text("(268x197)");
//            $('#md_th').text("(268x398)");
//            $('#lg_th').text("(870x570)");
//        }
//        else if(contType=="AUDIOEBOOK")
//        {
//            $('#sm_th').text("(400x565)");
//            $('#md_th').text("(400x565)");
//            $('#lg_th').text("(400x565)");
//        }
//        else
//        {
//            $('#sm_th').text("(200x200)");
//            $('#md_th').text("(420x240)");
//            $('#lg_th').text("(640x480)");
//        }
//        
//    });
        
        
        
        
        
        
        $('.edit_banner_master_status').click(function()
        {
//            alert('okmm');
            var elt = $(this);
            var nextElt = $(this).next();
            var bannerId = $(this).attr("title");
            var status = $(this).next().val();
//            alert(status);
            status==="0"?status="1":status="0";
//            alert(status);
            $.ajax(
          {
              url: "edit_banner_master_status.htm",
              type: "GET",
              data: {banner_id: bannerId,
                    status: status},
              success: function (data) {
//                alert(data);

//                var r = $(data).find("#reply").html();
                if($(data).find("#response").html()=="success")
                {
                    if($(data).find("#status").html()=="1")
                    {
                        elt.text("Enabled");
                        elt.removeClass("btn-danger");
                        elt.addClass("btn-success");
                        nextElt.val("1");
                    }
                    else if($(data).find("#status").html()=="0")
                    {
                        elt.text("Disabled");
                        elt.addClass("btn-danger");
                        elt.removeClass("btn-success");
                        nextElt.val("0");
                    }
                }
                else
                {
                    alert("Not updated");
                }

                  
            }

          });
            return false;
        });
        
        $('.edit_content_portal_master_status').click(function()
        {
//            modifyStatus("edit_content_portal_master_status.htm", $(this));
//            alert('ok');
            var elt = $(this);
            var nextElt = $(this).next();
            var portalId = $(this).attr("title");
            var status = $(this).next().val();
//            alert(status);
            status==="0"?status="1":status="0";
//            alert(status);
            $.ajax(
          {
              url: "edit_content_portal_master_status.htm",
              type: "GET",
              data: {id: portalId,
                    status: status},
              success: function (data) {
//                alert(data);

//                var r = $(data).find("#reply").html();
                if($(data).find("#response").html()=="success")
                {
                    if($(data).find("#status").html()=="1")
                    {
                        elt.text("Enable");
                        elt.removeClass("btn-danger");
                        elt.addClass("btn-success");
                        nextElt.val("1");
                    }
                    else if($(data).find("#status").html()=="0")
                    {
                        elt.text("Disable");
                        elt.addClass("btn-danger");
                        elt.removeClass("btn-success");
                        nextElt.val("0");
                    }
                }
                else
                {
                    alert("Not updated");
                }

                  
            }

          });
            
            return false;
        });
        
        $('.edit_menu_master_status').click(function()
        {
            
//            modifyStatus("edit_menu_master_status.htm", $(this));
            
//            var elt = $(this);
//            var nextElt = $(this).next();
//            var bannerId = $(this).attr("title");
//            var status = $(this).next().val();
////            alert(status);
//            status==="0"?status="1":status="0";
////            alert(status);
//            $.ajax(
//          {
//              url: "edit_banner_master_status.htm",
//              type: "GET",
//              data: {banner_id: bannerId,
//                    status: status},
//              success: function (data) {
////                alert(data);
//
////                var r = $(data).find("#reply").html();
//                if($(data).find("#response").html()=="success")
//                {
//                    if($(data).find("#status").html()=="1")
//                    {
//                        elt.text("Enabled");
//                        elt.removeClass("btn-danger");
//                        elt.addClass("btn-success");
//                        nextElt.val("1");
//                    }
//                    else if($(data).find("#status").html()=="0")
//                    {
//                        elt.text("Disabled");
//                        elt.addClass("btn-danger");
//                        elt.removeClass("btn-success");
//                        nextElt.val("0");
//                    }
//                }
//                else
//                {
//                    alert("Not updated");
//                }
//
//                  
//            }
//
//          });
//            return false;
            
            var elt = $(this);
            var nextElt = $(this).next();
            var menuId = $(this).attr("title");
            var status = $(this).next().val();
            status==="0"?status="1":status="0";
            $.ajax(
          {
              url: "edit_menu_master_status.htm",
              type: "GET",
              data: {id: menuId,
                    status: status},
              success: function (data) {
                if($(data).find("#response").html()=="success")
                {
                    if($(data).find("#status").html()=="1")
                    {
                        elt.text("Enabled");
                        elt.removeClass("btn-danger");
                        elt.addClass("btn-success");
                        nextElt.val("1");
//                        elt.removeClass("del");
//                        elt.addClass("ok");
//                        nextElt.val("1");
                    }
                    else if($(data).find("#status").html()=="0")
                    {
                        elt.text("Disabled");
                        elt.addClass("btn-danger");
                        elt.removeClass("btn-success");
                        nextElt.val("0");
//                        elt.removeClass("ok");
//                        elt.addClass("del");
//                        nextElt.val("0");
                    }
                }
                else
                {
                    alert("Not updated");
                }

                  
            }

          });
            return false;
        });
        
        
        $('.edit_menu_child_status').click(function()
        {
            
//            modifyStatus("edit_menu_child_status.htm", $(this));
//            
//            return false;

            var elt = $(this);
            var nextElt = $(this).next();
            var menuChildId = $(this).attr("title");
            var status = $(this).next().val();
            status==="0"?status="1":status="0";
            $.ajax(
          {
              url: "edit_menu_child_status.htm",
              type: "GET",
              data: {id: menuChildId,
                    status: status},
              success: function (data) {
                if($(data).find("#response").html()=="success")
                {
                    if($(data).find("#status").html()=="1")
                    {
                        elt.text("Enabled");
                        elt.removeClass("btn-danger");
                        elt.addClass("btn-success");
                        nextElt.val("1");
//                        elt.removeClass("del");
//                        elt.addClass("ok");
//                        nextElt.val("1");
                    }
                    else if($(data).find("#status").html()=="0")
                    {
                        elt.text("Disabled");
                        elt.addClass("btn-danger");
                        elt.removeClass("btn-success");
                        nextElt.val("0");
//                        elt.removeClass("ok");
//                        elt.addClass("del");
//                        nextElt.val("0");
                    }
                }
                else
                {
                    alert("Not updated");
                }

                  
            }

          });
            return false;


        });
        
        
        $('.edit_menu_mapping_status').click(function()
        {
            
//            modifyStatus("edit_menu_mapping_status.htm", $(this));
//            
//            return false;

            var elt = $(this);
            var nextElt = $(this).next();
            var menuMappingId = $(this).attr("title");
            var status = $(this).next().val();
            status==="0"?status="1":status="0";
            $.ajax(
          {
              url: "edit_menu_mapping_status.htm",
              type: "GET",
              data: {id: menuMappingId,
                    status: status},
              success: function (data) {
                if($(data).find("#response").html()=="success")
                {
                    if($(data).find("#status").html()=="1")
                    {
                        elt.text("Enabled");
                        elt.removeClass("btn-danger");
                        elt.addClass("btn-success");
                        nextElt.val("1");
//                        elt.removeClass("del");
//                        elt.addClass("ok");
//                        nextElt.val("1");
                    }
                    else if($(data).find("#status").html()=="0")
                    {
                        elt.text("Disabled");
                        elt.addClass("btn-danger");
                        elt.removeClass("btn-success");
                        nextElt.val("0");
//                        elt.removeClass("ok");
//                        elt.addClass("del");
//                        nextElt.val("0");
                    }
                }
                else
                {
                    alert("Not updated");
                }

                  
            }

          });
            return false;

        });
        
        
        $('.edit_content_master_status').click(function()
        {
//            modifyStatus("edit_content_master_status.htm", $(this));
            
            return false;
        });
        
        
        
        
        
        
        $('.edit_content_category_mapping_status').click(function()
        {
            modifyStatus("edit_content_category_mapping_status.htm", $(this));
            
            return false;
        });
        
        
        
        
        
//        $('.edit_content_file_status').click(function()
//        {
//            modifyStatus("edit_content_file_status.htm", $(this));
//            
//            return false;
//        });
        
        
//        $('.edit_portal_cat_map_status').click(function()
//        {
//            modifyStatus("edit_portal_cat_map_status.htm", $(this));
//            
//            return false;
//        });

        
        //Move to updated
        $('#menu_id').change(function()
        {
            var menuId = $(this).val();
            var userId = $('#userIdPrv').val();
            if(menuId!="none" && userId!="none")
            {
            $.ajax(
          {
              url: "load_child_menu.htm",
              type: "GET",
              data: {menu_id: menuId,
                    user_id: userId},
              success: function (data) {

                  $('#replace').html($(data).filter("#replace").html());
            }

          });
      }
        });
        
         $('#userIdPrv').change(function()
        {
             var userId = $(this).val();
            var menuId = $('#menu_id').val();
            if(menuId!="none" && userId!="none")
            {
            $.ajax(
          {
              url: "load_child_menu.htm",
              type: "GET",
              data: {menu_id: menuId,
                    user_id: userId},
              success: function (data) {

                  $('#replace').html($(data).filter("#replace").html());
            }

          });
      }
        });
        
        
        $('table').delegate('.add_priviledge', 'click', function()
        {

            var elt = $(this);//needed
            var roleType = $('#roleType').val();//needed
            var nextElt = elt.next();
            var menuId = elt.attr("title");//needed
            var menuMappingStatus = elt.next().val();//needed
           
           
           
            menuMappingStatus==="0"?menuMappingStatus="1":menuMappingStatus="0";
//            alert(status);
            $.ajax(
          {
              url: "add_priviledge.htm",
              type: "GET",
              data: {menuId: menuId,
                     roleType: roleType,
                    menuMappingStatus: menuMappingStatus},
              success: function (data) {
                if($(data).find("#response").html()=="success")
                {
                    if($(data).find("#status").html()=="1")
                    {

//                        elt.attr("checked", "checked");

                        elt.text("Enabled");
                        elt.removeClass("btn-danger");
                        elt.addClass("btn-success");
                        nextElt.val("1");
                    }
                    else if($(data).find("#status").html()=="0")
                    {

//                        elt.removeAttr("checked");
                        elt.text("Disabled");
                        elt.removeClass("btn-success");
                        elt.addClass("btn-danger");
                        nextElt.val("0");
                    }
                    
                }
                else
                {
                    alert("Not updated");
                }

                  
            }

          });
            
//            
            return false;
        
//              url: "add_priviledge.htm.htm",
             
        });
        
        
        $('table').delegate('.add_priviledge_child', 'click', function()
        {

        alert('cqq');
            var elt = $(this);//needed

            var menuId = $(this).parent().parent().children().get(1).value;
            
            var roleType = $('#roleType').val();//needed
//            var menuId = $('#menuId').val();//needed
            var nextElt = elt.next();
            var menuChildId = elt.attr("title");//needed
            var menuChildMappingStatus = elt.next().val();//needed
           
           
        
            menuChildMappingStatus==="0"?menuChildMappingStatus="1":menuChildMappingStatus="0";
//            alert(status);
            $.ajax(
          {
              url: "add_priviledge_child.htm",
              type: "GET",
              data: {menuId: menuId,
                     roleType: roleType,
                     menuChildId: menuChildId,
                    menuChildMappingStatus: menuChildMappingStatus},
              success: function (data) {
                if($(data).find("#response").html()=="success")
                {
                    if($(data).find("#status").html()=="1")
                    {

//                        elt.attr("checked", "checked");

                        elt.text("Enabled");
                        elt.removeClass("btn-danger");
                        elt.addClass("btn-success");
                        nextElt.val("1");
                    }
                    else if($(data).find("#status").html()=="0")
                    {

//                        elt.removeAttr("checked");
                        elt.text("Disabled");
                        elt.removeClass("btn-success");
                        elt.addClass("btn-danger");
                        nextElt.val("0");
                    }
                    
                }
                else
                {
                    alert("Not updated");
                }

                  
            }

          });
            
//            
            return false;
        
//              url: "add_priviledge.htm.htm",
             
        });
        
        $('#roleType').change(function()
        {
            $('tbody').html('');
        })
        
        
        
        
        $('#close').click(function()
        {
             var selectId = $('#hide_rmk').val();
            $('#'+selectId+'').val("none");
            
            var modal = document.getElementById('myModal');
            modal.style.display = "none";
            
            
            
        });
        
        $('#close2').click(function()
        {
            var modal2 = document.getElementById('myModal2');
            modal2.style.display = "none";
            
        });
        
//        $('.view_rmk').click(function()
//        {
//            var remarkes = $(this).next().val();
//            $('#rmk_area').val(remarkes);
//            $('#proceed_btn').hide();
//            $('#rmk_area').attr('disabled', 'disabled');
//            var modal = document.getElementById('myModal');
//            modal.style.display = "block";
//            
//            
//            
//            return false;
//            
//        });
        
        
        


        
        
        
        $('#close_btn').click(function()
        {
            var selectId = $('#hide_rmk').val();
            $('#'+selectId+'').val('none');
            var modal = document.getElementById('myModal');
            modal.style.display = "none";
            
        });
        
        $('#close_btn2').click(function()
        {
            var modal2 = document.getElementById('myModal2');
            modal2.style.display = "none";
            
        }); 
//         $("#replace").delegate(".thumnnail", 'click', function(){
        $(".thumnnail").click(function(){
//            var imgUrl = $(this).text();
//            var imgUrl = $(this).attr('title');
            var imgUrl = $(this).next().val();
            alert(imgUrl);
            $.ajax(
              {
                  url: "get_thumbnail.htm",
                  type: "GET",
                  data: {thumbnail_small_url: imgUrl},
                  success: function (data) {
                      
                      $('#thumb').attr('src', $(data).find("#img_url").text())
//                      $('#pp').text($(data).find("#img_url").text());
                      var modal2 = document.getElementById('myModal2');
                      modal2.style.display = "block";

                }

              });
            
//            return false;
        });
        
        $('#search_by').change(function()
        {
           
            var option = $(this);
            
            if(option.val()=='title')
            {
//                alert('wao2211');
                organise($('#title-div'), $('#title'));
                $('#typeN').removeAttr('disabled');
                $('#statusN').removeAttr('disabled');
                
            }
            else if(option.val()=='type')
            {
                organise($('#type-div'), $('#type'));
                $('#statusN').removeAttr('disabled');
                $('#typeN').attr('disabled', 'disabled');
                

            }
            else if(option.val()=='desc')
            {
                organise($('#desc-div'), $('#desc'));
                $('#typeN').removeAttr('disabled');
                $('#statusN').removeAttr('disabled');

            }
            else if(option.val()=='status')
            {
                organise($('#status-div'), $('#status'));
                $('#typeN').removeAttr('disabled');
                $('#statusN').attr('disabled', 'disabled');

            }
            

            
        });
        
        
        
        function organise(elt, innerElt)
        {
            $('.option-div').attr('style', 'display: none');
            $('.option-val').removeAttr('required');

            elt.removeAttr('style');
            innerElt.attr('required', 'required');
        }
        
        
        $( "#dialog" ).dialog({
	autoOpen: false,
	width: 400,
	buttons: [
		{
			text: "Ok",
			click: function() {
				$( this ).dialog( "close" );
			}
		},
		{
			text: "Cancel",
			click: function() {
				$( this ).dialog( "close" );
			}
		}
	]
});

    
        
//        $('.store_reverce').change(function(){
//            alert('now');
//        });
        
//        $('.edit_user_detail').click(function()
//        {
//            modifyStatus("edit_user_detail.htm", $(this));
//            
//            return false;
//        });
        
//        $('.mm_url').click(function()
//        {
//            
//            alert("OK");
//            
//        });
        
//        $('.mc_url').click(function()
//        {
//            var url = $(this).attr("href");
////            alert(url);
//            $.ajax(
//          {
//              url: url,
//              type: "GET",
//              
//              success: function (data) {
//                alert(data);
//
//                  $('.container').html($(data).filter(".container").html());
//            }
//
//          });
//            return false;
//        });
//        
//        
//        $("#container").delegate("#adduserbtn", 'click', function(){
//             
//             alert('ok');
//             
//             return false;
//             
//         });
        
//        $('.input_edit').hide();
//        $('.item').hide();
//        
//        $("#update").delegate("#role_setup_save", 'click', function(){
//             
//             alert('readyUp???');
//             
//             var roleId = $('#role_id_setup').val();
//             var roleName = $('#role_name_setup').val();
//             
//             
//             
//             $('#update').load('role_setup_save.htm?role_id=' + roleId + '&role_name=' + encodeURIComponent(roleName) + ' #update');
//             
//             $.ajax(
//                          {
//                              url: "role_setup_save.htm",
//                              type: "GET",
//                              data: {role_id : roleId,
//                                    role_name : encodeURIComponent(roleName)},
//                              success: function (data) {
//                                alert(data);
//                                
//                                $('#update').html(data);
//                            
//                            }
//                              
//                          });
//             
//         });
//         
//         $("#update").delegate("#course_setup_save", 'click', function(){
//             
//             alert('ready?');
//             
//             var courseId = $('#course_id_setup').val();
//             var courseName = $('#course_name_setup').val();
//             var courseBilling = $('#course_billing_setup').val();
//             
//             
//             
//             
//             $('#update').load('course_setup_save.htm?courseId=' + courseId + '&courseName=' + encodeURIComponent(courseName) + '&courseBilling=' + encodeURIComponent(courseBilling) + ' #update');
//             
//         });
//        
//        $("#update").delegate("#center_setup_save", 'click', function(){
//             
//             alert('ready111?');
//             
//             var centerId = $('#center_id_setup').val();
//             var centerName = $('#center_name_setup').val();
//             var centerAddress = $('#center_address_setup').val();
//             
//             
//             $('#update').load('center_setup_save.htm?centerId=' + centerId + '&centerName=' + encodeURIComponent(centerName) + '&centerAddress=' + encodeURIComponent(centerAddress) + ' #update');
//             
//             $.ajax(
//                          {
//                              url: "center_setup_save.htm",
//                              type: "GET",
//                              data: {centerId : centerId,
//                                    centerName : encodeURIComponent(centerName)},
//                              success: function (data) {
//                                alert(data);
//                                
//                                $('#update').html(data);
//                            
//                            }
//                              
//                          });
//             
//         });
//        
//        $("#update").delegate("#batch_status_setup_save", 'click', function(){
//             
//             alert('ready?');
//             
//             var batchStatusId = $('#batch_status_id_setup').val();
//             var batchStatusName = $('#batch_status_name_setup').val();
//             
//             
//             
//             $('#update').load('batch_status_save.htm?batch_status_id=' + batchStatusId + '&batch_status_name=' + encodeURIComponent(batchStatusName) + ' #update');
//             
//         });
//        
//        $("#update").delegate("#filter_center", 'click', function(){
//
//             var centerId = $('#filter_center_id').val();
//             if(centerId!=="none")
//             {
//
//               $.ajax(
//                             {
//                                 url: "filter_by_center.htm",
//                                 type: "GET",
//                                 data: {center_id : centerId},
//                                 success: function (data) {
//
//                               var r = $(data).find("#reply").html();
//                               if(r==='empty')
//                               {
//                                   $('#msg').show();
//                               }
//                               else
//                               {
//                                   $('#msg').hide();
//                                   $('table').html(data);
//   //                            $('#update').load(data + ' #update');
//                               }
//                           }
//
//                             });
//             }
//             
//         });
//         
//         
//         $("#update").delegate(".btn_staff_edit", 'click', function(){
//
//             var staffId = $(this).attr('title');
//
//             $('#update').load('get_staff_info_edit.htm?staff_id=' + staffId + ' #update');
//             
//         });
//         
//         $("#staff_profile").click(function(){
//             
//            
//             var staffId = $(this).attr('title');
//
//             $('#update').load('staff_profile.htm?staff_id=' + staffId + ' #update');
//             
//         });
//         
//         $("#update").delegate("#new_staff", 'click', function(){
//
//             $('#update').load('new_staff.htm #update');
//             
//         });
//         
//         $("#update").delegate("#staff_save_frm", 'submit', function(){
//             
//             alert("ok");
//             var staffId = $('#staff_id').val();
//             var firstName = $('#first_name').val();
//             var lastName = $('#last_name').val();
//             var email = $('#email').val();
//             var address = $('#address').val();
//
//             var phoneNumber = $('#phone_number').val();
//             
//             var password = $('#password').val();
//             var username = $('#username').val();
//             var role = $('#role').val();
//             var center = $('#center').val();
//             var department = $('#department').val();
//             var dateOfEmployment =$('#d_o_e').val();
////             var batch = $('#batch').val();
//             alert("now validate");
//             if(new String(staffId).trim().length!=0 || new String(firstName).trim().length!=0 || new String(lastName).trim().length!=0 || new String(email).trim().length!=0 || new String(address).trim().length!=0 || new String(phoneNumber).trim().length!=0 || new String(password).trim().length!=0 || new String(username).trim().length!=0 || new String(role).trim().length!=0 || new String(center).trim().length!=0 || new String(department).trim().length!=0 || new String(dateOfEmployment).trim().length!=0)
//              {
//                  alert('Complete it');
////              }
////              else
////              {
//                  alert('Complete alreadyY');
//
//                  $.ajax(
//                          {
//                              url: "register_new_staff.htm",
//                              type: "POST",
//                              data: $(this).serialize(),
//                              success: function (data) {
//                            alert(data);
//                            
//                            $('#update').html(data);
////                            $('#update').load(data + ' #update');
//                            
//                        }
//                              
//                          });
//              }
//             
////             $('#payment').val(diff);
//             
//             
////             $('#balance').val(diff);
//             
//             return false;
//             
//         });
//         
//         $("#update").delegate("#staff_edit_frm", 'submit', function(){
//             
//             
//             var staffId = $('#staff_id').val();
//             var firstName = $('#first_name').val();
//             var lastName = $('#last_name').val();
//             var email = $('#email').val();
//             var address = $('#address').val();
//
//             var phoneNumber = $('#phone_number').val();
//             
//             var password = $('#password').val();
//             var username = $('#username').val();
//             var role = $('#role').val();
//             var center = $('#center').val();
//             var department = $('#department').val();
//             var dateOfEmployment =$('#d_o_e').val();
////             var batch = $('#batch').val();
//             
//             if(new String(staffId).trim().length!=0 || new String(firstName).trim().length!=0 || new String(lastName).trim().length!=0 || new String(email).trim().length!=0 || new String(address).trim().length!=0 || new String(phoneNumber).trim().length!=0 || new String(password).trim().length!=0 || new String(username).trim().length!=0 || new String(role).trim().length!=0 || new String(center).trim().length!=0 || new String(department).trim().length!=0 || new String(dateOfEmployment).trim().length!=0)
//              {
//
//                  $.ajax(
//                          {
//                              url: "edit_staff.htm",
//                              type: "POST",
//                              data: $(this).serialize(),
//                              success: function (data) {
//      
//                            $('#update').html(data);
//
//                        }
//                              
//                          });
//              }
//
//             return false;
//             
//         });
//         
//         $("#update").delegate("#config_save_frm", 'submit', function(){
//             
//             alert("ok");
//             var compName = $('#comp_name').val();
//             var headerText = $('#header_text').val();
//             var flowText = $('#flow_text').val();
//             var licenseKey = $('#license_key').val();
//             var licenseDate = $('#license_date').val();
//             var currentDate = $('#current_date').val();
//
//             var macNo = $('#mac_no').val();
//             
//             
////             var batch = $('#batch').val();
//             alert("now validate");
//             if(new String(compName).trim().length!=0 || new String(headerText).trim().length!=0 || new String(flowText).trim().length!=0 || new String(licenseKey).trim().length!=0 || new String(licenseDate).trim().length!=0 || new String(currentDate).trim().length!=0 || new String(macNo).trim().length!=0)
//              {
//                  alert('Complete it');
////              }
////              else
////              {
//                  alert('Complete alreadyY');
//
//                  $.ajax(
//                          {
//                              url: "save_config.htm",
//                              type: "POST",
//                              data: $(this).serialize(),
//                              success: function (data) {
//                            alert(data);
//                            
//                            $('#update').html(data);
////                            $('#update').load(data + ' #update');
//                            
//                        }
//                              
//                          });
//              }
//             
////             $('#payment').val(diff);
//             
//             
////             $('#balance').val(diff);
//             
//             return false;
//             
//         });
//         
//         $("#update").delegate("#config_edit_frm", 'submit', function(){
//             
//             var configId = $('#config_id').val();
//             
//             alert(configId);
//             
//             var compName = $('#comp_name').val();
//             var headerText = $('#header_text').val();
//             var flowText = $('#flow_text').val();
//             var licenseKey = $('#license_key').val();
//             
//             var appUser = $('#role').val();
//             var adminUser = $('#admin_role').val();
//
//              if(new String(compName).trim().length!=0 || new String(headerText).trim().length!=0 || new String(flowText).trim().length!=0 || new String(licenseKey).trim().length!=0 || new String(appUser).trim().length!=0 || new String(adminUser).trim().length!=0)
//              {
//                  $.ajax(
//                          {
//                              url: "edit_config.htm",
//                              type: "POST",
//                              data: $(this).serialize(),
//                              success: function (data) {
//                            
//                            $('#update').html(data);
//                        }
//                              
//                          });
//              }
//
//             return false;
//             
//         });
//         
//         $('#update').delegate('#add_pay_btn', 'click', function(){
//              
//              //alert($(this).next().val());
//              
//              var id = $('#id_course').val();
//              
//              var billing = $('#amt').val();
//             var previousPayment = $('#prev_pay').val();
//             var balance = $('#bal').val();
//             
//             var newPayment = $('#new_pay').val();
//             
//             var courseId = $('#course_pay').val();
//             
////             var currentPay = (newPayment + previousPayment);
////             
////             balance = billing - currentPay;
//             
//             
//             var searchBy = $('#search_by').val();
//             var centerId = $('#center_id').val();
//             var email = $('#email').val();
//             var phone = $('#phone').val();
//             
//             
//             
////             if(new String(amount).trim().length!=0 || new String(balance).trim().length!=0 || new String(previousPayment).trim().length!=0 || new String(searchBy).trim().length!=0 || new String(centerId).trim().length!=0 || new String(previousEmail).trim().length!=0 || new String(newPayment).trim().length!=0)
//              if(new String(balance).trim().length!==0 || new String(billing).trim().length!==0 || new String(previousPayment).trim().length!==0 || new String(newPayment).trim().length!==0 || new String(searchBy).trim().length!==0 || new String(centerId).trim().length!==0 || new String(email).trim().length!==0 || new String(courseId).trim().length!==0)
//              {
//                  
//                  if(searchBy==="email")
//                  {
//                      
////                    $('#update').load('add_pay_by_email.htm?balance='+balance + '&billing=' + billing + '&previous_payment=' + previousPayment + '&new_payment=' + newPayment + '&email_phone=' + email + '&center_id=' + centerId + '&course_id=' + courseId + '&search_by=' + searchBy + ' #update');
//                    $('#update').load('add_pay_by_id_course.htm?balance='+balance + '&billing=' + billing + '&previous_payment=' + previousPayment + '&new_payment=' + newPayment + '&id=' + id + '&center_id=' + centerId + '&course_id=' + courseId + '&search_by=' + searchBy + ' #update');
//                  }
//                  else if(searchBy==="phone")
//                  {
//
////                        $('#update').load('add_pay_by_email.htm?balance='+balance + '&billing=' + billing + '&previous_payment=' + previousPayment + '&new_payment=' + newPayment + '&email_phone=' + phone + '&center_id=' + centerId + '&course_id=' + courseId + '&search_by=' + searchBy + ' #update');
//                        $('#update').load('add_pay_by_id_course.htm?balance='+balance + '&billing=' + billing + '&previous_payment=' + previousPayment + '&new_payment=' + newPayment + '&id=' + id + '&center_id=' + centerId + '&course_id=' + courseId + '&search_by=' + searchBy + ' #update');
//                  }
//              }
//             
//             
//              
//              
//              
//          });
//         
//         
//         $('#update').delegate('#change_center_btn', 'click', function(){
//              
//              //alert($(this).next().val());
//              
//              var id = $('#id_course').val();
//              
//              var frmCenterId = $('#frm_center_id').val();
//              var courseId = $('#course_id').val();
////             var previousPayment = $('#prev_pay').val();
////             var balance = $('#bal').val();
////             
////             var newPayment = $('#new_pay').val();
////             
////             var courseId = $('#course_pay').val();
//             
////             var currentPay = (newPayment + previousPayment);
////             
////             balance = billing - currentPay;
//             
//             alert('userrr: ' + frmCenterId);
//             var searchBy = $('#search_by').val();
//             var toCenterId = $('#to_center_id').val();
//             var email = $('#email').val();
//             var phone = $('#phone').val();
//             
//             
//             
////             if(new String(amount).trim().length!=0 || new String(balance).trim().length!=0 || new String(previousPayment).trim().length!=0 || new String(searchBy).trim().length!=0 || new String(centerId).trim().length!=0 || new String(previousEmail).trim().length!=0 || new String(newPayment).trim().length!=0)
//              if(new String(frmCenterId).trim().length!==0 || new String(courseId).trim().length!==0 ||  new String(searchBy).trim().length!==0 || new String(email).trim().length!==0 || new String(courseId).trim().length!==0)
//              {
//                  alert('userrr: 1' + searchBy);
//                  if(searchBy==="email")
//                  {
//                      alert('userrr: 2');
////                    $('#update').load('change_center_by_option.htm?frm_center_id='+frmCenterId + '&option=' + email + '&to_center_id=' + toCenterId + '&course_id=' + courseId + '&search_by=' + searchBy + ' #update');
//                    $('#update').load('change_center_by_id_course.htm?frm_center_id='+frmCenterId + '&id=' + id + '&to_center_id=' + toCenterId + '&course_id=' + courseId + '&search_by=' + searchBy + ' #update');
//                  }
//                  else if(searchBy==="phone")
//                  {
//
////                        $('#update').load('change_center_by_option.htm?frm_center_id='+frmCenterId + '&option=' + phone + '&to_center_id=' + toCenterId + '&course_id=' + courseId + '&search_by=' + searchBy + ' #update');
//                        $('#update').load('change_center_by_id_course.htm?frm_center_id='+frmCenterId + '&id=' + id + '&to_center_id=' + toCenterId + '&course_id=' + courseId + '&search_by=' + searchBy + ' #update');
//                  }
//              }
//             
//             
//              
//              
//              
//          });
//         
//         $("#update").delegate("#filter_batch", 'click', function(){
//             
//             
//             
//             var status = $('#filter_status').val();
//             var centerId = $('#center_idd').val();
//             
//             
//             
////             $('#stu_tb').load('filter_batch.htm?centerId=' + centerId + '&status=' + status + ' #stu_tb');
//             $.ajax(
//                          {
//                              url: "filter_batch.htm",
//                              type: "GET",
//                              data: {centerId : centerId,
//                                     status : status},
//                              success: function (data) {
//                            
//                            var r = $(data).find("#reply").html();
//                            if(r!==null)
//                            {
//                                
//                                $('#msg').show();
//                            }
//                            else
//                            {
//                                $('#msg').hide();
//                                $('#stu_tb').html(data);
//                                
//                            }
//                            
//                        }
//                              
//                          });
//             
//         });
//         
////         okkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk
//        
//        
////        ------Admin Start----------
//        $("#update").delegate("#admin_search_bt", 'click', function(){
//             
//             alert('ready?');
//             
////             var status = $('#filter_status').val();
//             var centerId = $('#center_id').val();
////             var courseId = $('#course_id').val();
////             var courseTitle = $('#course_title').val();
//             
//             
//             alert(centerId+"");
//             
//             $('#update').load('admin_search_bt.htm?center_id=' + centerId + ' #update');
//             
//         });
//         
//         $("#update").delegate("#admin_stu_search_bt", 'click', function(){
//             alert('yeahccc');
//             
////             var stu_find = $('#stu_find').val();
//             
////             if(stu_find != "")
////             {
//
//                var centerId = $('#center_id').val();
////                var centerId = $('#center_idd').val();
//                 if($('#email').is(':checked'))
//                 {
//                     //alert('checked');
//                     var email = $('#stu_find').val();
//                     
//                     if(email!="")
//                     {
//                        $('#update').load('admin_find_stu_by_email2.htm?centerId=' + centerId + '&email='+email + ' #update');
//                     }
//                     else
//                     {
//                         //display toast error
//                     }
//                     
//                 }
//                 else if($('#phone').is(':checked'))
//                 {
//                     //alert('checked');
//                     var phone = $('#stu_find').val();
//                     if(phone!="")
//                     {
//                         alert('1confirmed');
//                        $('#update').load('admin_find_stu_by_phone2.htm?centerId=' + centerId + '&phone='+phone + ' #update');
//                        alert('confirmed');
//                     }
//                     else
//                     {
//                         //display toast error
//                     }
//                 }
////                 else if($('#teller_no').is(':checked'))
////                 {
////                     alert('checked');
////                 }
////             }
////             else
////             {
////                 
////             }
//             return false;
//         });
//        
//        $("#update").delegate("#admin_enquiry_search", 'click', function(){
//              
////              alert('reg');
//              
//              var centerId = $('#center_id').val();
//              alert(centerId);
////              var counselorId = $('#staff_id').val();
//              
//              $('#update').load('all_enquiry_by_center.htm?center_id=' + centerId +' #update');
//          });
//        
//        
//        
//        $("#update").delegate("#admin_view_stu_batch", 'click', function(){
//              
////              alert('reg');
//              
////              var centerId = $('#center_id').val();
//              alert("centerId");
////              var counselorId = $('#staff_id').val();
//              
//              $('#update').load('admin_view_stu_batch.htm #update');
//          });
//        
//        
//        $("#update").delegate("#admin_filter_stu_batch", 'click', function(){
////             jQuery('')
//             
//             jQuery('body').css('cursor: poiter');
////             preventDefault();
//             var course = $('#filter_stu_course').val();
//             var centerId = $('#center_id').val();
//             
//             var title = document.getElementById(course).innerHTML;
//             $('#stu_tb').load('admin_getstu.htm?course='+course + '&centerId='+ centerId + '&title=' + encodeURIComponent(title) + ' #stu_tb');
//             
//             return false;
//             
//         });
//        
//        $('#update').delegate('.admin_stu_group', 'click', function(){
//              
//              alert($(this).next().val());
//              $(this).next().val();
//              var centerId = $('#center_id').val();
//              alert(centerId);
//              var title = document.getElementById('title').innerHTML;
//              $('#update').load('admin_getstubatch.htm?stu_batch='+$(this).next().val() + '&center_id=' + centerId + '&course_title=' + encodeURIComponent(title) + ' #update');
//          });
//        
////        -----------Admin Ends---------------
//
//        
//        
//        
//          
//          $("#update").delegate(".config_delete", 'click', function(){
//             
//             
//             var configId = $(this).attr('title');
////             alert(roleId);
//             
//             $('#update').load('config_delete.htm?config_id=' + configId + ' #update');
//             
//         });
//         
//         $("#update").delegate("#filter_stu_batch", 'click', function(){
//             
//             alert('ready?');
//             
//             var status = $('#filter_status').val();
//             var centerId = $('#center_idd').val();
//             var courseId = $('#course_id').val();
//             var courseTitle = $('#course_title').val();
//             
//             
//             alert(courseTitle+"");
//             
//             $('#stu_tb').load('filter_stu_batch.htm?center_id=' + centerId + '&course_id=' + courseId + '&course_title=' + encodeURI(courseTitle) + '&status=' + status + ' #stu_tb');
//             
//         });
//         
//         
//         $('#center_view').click(function()
//         {
////             jQuery('')
//             
////             jQuery('body').css('cursor: poiter');
//////             preventDefault();
////             var course = $(this).attr('id');
////             var centerId = $('#center_idd').val();
////             
////             var title = document.getElementById(course).innerHTML;
////             $('#update').load('course_view.htm?course='+course + '&centerId='+ centerId + '&title=' + encodeURIComponent(title) + ' #update');
//             $('#update').load('center_view.htm #update');
//             
////             return false;
//             
//         });
//         
//         $('#student_view').click(function()
//         {
////             jQuery('')
//             
////             jQuery('body').css('cursor: poiter');
//////             preventDefault();
////             var course = $(this).attr('id');
////             var centerId = $('#center_idd').val();
////             
////             var title = document.getElementById(course).innerHTML;
////             $('#update').load('course_view.htm?course='+course + '&centerId='+ centerId + '&title=' + encodeURIComponent(title) + ' #update');
//             $('#update').load('center_admin_view.htm #update');
//             
////             return false;
//             
//         });
//        
//         $('.content').hide();
//		 $('.header').toggle(function(){
//			 $(this).next().slideDown('slow');
//			 
//			 
//		 }, function(){
//				$(this).next().slideUp('slow');
//		 });
//                 
//         $('.course').click(function()
//         {
////             jQuery('')
//             
//             jQuery('body').css('cursor: poiter');
////             preventDefault();
//             var course = $(this).attr('id');
//             var centerId = $('#center_idd').val();
//             
//             var title = document.getElementById(course).innerHTML;
//             $('#update').load('getstu.htm?course='+course + '&centerId='+ centerId + '&title=' + encodeURIComponent(title) + ' #update');
//             
//             return false;
//             
//         });
//         
//         
//         $('#update').delegate('#course_select_btn', 'click', function(){
////             jQuery('')
//             
//             jQuery('body').css('cursor: poiter');
////           
//             var course = $('#course_select').val();
//             
//             var centerId = $('#center_idd').val();
//             
////             var title = document.getElementById(course).innerHTML;
//            var title = 'Set this';
//            
//            $.ajax(
//                          {
//                              url: "getstu.htm",
//                              type: "GET",
//                              data: {course : course,
//                                     centerId : centerId,
//                                     title : encodeURIComponent(title)},
//                              success: function (data) {
//                            
//                            var r = $(data).find("#reply").html();
//                            if(r==='empty')
//                            {
//                                
//                                $('#msg').show();
//                            }
//                            else
//                            {
//                                $('#msg').show();
//                                $('#update').html(data);
//                                
//                            }
//                            
//                        }
//                              
//                          });
//            
//            
////             $('#update').load('getstu.htm?course='+course + '&centerId='+ centerId + '&title=' + encodeURIComponent(title) + ' #update');
//             
//             return false;
//             
//         });
//         
//         $('#course_selection').click(function()
//         {
//             $('#update').load('get_all_courses.htm #update');
//             
//             return false;
//             
//         });
//         
////         $('#sign_out').click(function()
////         {
////             
//////             jQuery('body').css('cursor: poiter');
////////             preventDefault();
//////             var course = $(this).attr('id');
//////             var centerId = $('#center_idd').val();
//////             
//////             var title = document.getElementById(course).innerHTML;
//////             $('#update').load('getstu.htm?course='+course + '&centerId='+ centerId + '&title=' + encodeURIComponent(title) + ' #update');
////
////                $('#update').load('signout.htm?username='+username + '&centerId='+ centerId + '&title=' + encodeURIComponent(title) + ' #update');
////             
////             return false;
////             
////         });
//         
//          $('#update').delegate('.stu_group', 'click', function(){
//              
//              //alert($(this).next().val());
//              $(this).next().val();
//              var title = document.getElementById('title').innerHTML;
//              $('#update').load('getstubatch.htm?stu_batch='+$(this).next().val() + '&course_title=' + encodeURIComponent(title) + ' #update');
//          });
//          
//          
//          $('#update').delegate('.admin_stu_data', 'click', function(){
//              
//              alert($(this).next().val());
//              $(this).next().val();
////              var title = document.getElementById('title').innerHTML;
////              $('#update').load('getstudata.htm?email='+$(this).next().val() + '&course_title=' + encodeURIComponent(title) + ' #update');
//                $('#update').load('admin_find_stu_by_idcourse.htm?id='+$(this).next().val() + ' #update');
//          });
//          
//          
//          $('#update').delegate('.stu_data', 'click', function(){
//              
//              
//              $(this).next().val();
//
//                $('#update').load('find_stu_by_idcourse.htm?id='+$(this).next().val() + ' #update');
//
//          });
//          
//          $('#stu_reg').click(function(){
//              
//              //alert('reg');
//              var centerId = $('#center_idd').val();
//              $('#update').load('stureg.htm?centerId=' + centerId + ' #update');
//          });
//          $('#stu_search').click(function(){
//              
////              alert('reg');
//              
//              $('#update').load('stu_search.htm #update');
//          });
//          
//          $("#update").delegate("#admin_stu_search", 'click', function(){
//              
//              alert('reg');
//              
//              $('#update').load('admin_stu_search.htm #update');
//          });
//          
//          $("#update").delegate("#filter_by_counselor", 'change', function(){
//              
////              alert('reg');
//              
//              var centerId = $('#center_idd').val();
//              
//              var counselorId = $('#filter_by_counselor').val();
//              
//              $('#update').load('all_enquiry.htm?center_id=' + centerId + '&counselor_id=' + counselorId +' #update');
//          });
//          
////          $("#update").delegate("#filter_by_counselor", 'change', function(){
////              
////              alert("kkk");
////              
////              var centerId = $('#center_idd').val();
////              
////              var counselor = $('#filter_by_counselor').val();
////              
////              
////              $('#update').load('new_enquiry.htm?staffId=' + counselor + '&center_id=' + centerId + ' #update');
////          });
//          
//          $("#update").delegate(".enq_comment", 'click', function(){
//
//                var customerName = $(this).parent().parent().children().get(0).innerHTML;
//
//
//              var comments = $(this).next().val();
//
//              $('#myModalLabel').text(customerName);
//              $('#body_text').text(comments);
////              $('#myModal').modal('toggle');
////              
//          });
//          
//          $('#batch_setup').click(function(){
//              
//              
//              var centerId = $('#center_idd').val();
//              $('#update').load('batch_setup.htm?centerId=' + centerId + ' #update');
//          });
//          $('#new_enquiry').click(function(){
//              
//              var centerId = $('#center_idd').val();
//              
//              $('#update').load('new_enquiry.htm?staffId=' + $('#staff_id').val() + '&center_id=' + centerId + ' #update');
//          });
//          
////          $('#enquiry_search').click(function(){
////              
//////              alert('reg');
////              
////              $('#update').load('enquiry_search.htm #update');
////          });
//
//            $('#enquiry_search').click(function(){
//              
////              alert('reg');
//              
//              var centerId = $('#center_idd').val();
//              
//              var counselorId = $('#staff_id').val();
//              
//              $('#update').load('all_enquiry.htm?center_id=' + centerId + '&counselor_id=' + counselorId +' #update');
//          });
//          
//          
//          $("#update").delegate("#seach_enquiry_bt", 'click', function(){
//          
//          alert('checked');
//              var centerId = $('#center_idd').val();
//             if($('#enquiry_by_email').is(':checked'))
//             {
//                 //alert('checked');
//                 var email = $('#enquiry_val').val();
//
//                 if(email!="")
//                 {
//                    $('#update').load('find_enquiry_by_email.htm?center_id=' + centerId + '&email='+email + ' #update');
//                 }
//                 else
//                 {
//                     //display toast error
//                 }
//
//             }
//             else if($('#enquiry_by_phone').is(':checked'))
//             {
//                 alert('checked');
//                 var phone = $('#enquiry_val').val();
//
//                 if(phone!="")
//                 {
//                     alert('now');
//                    $('#update').load('find_enquiry_by_phone.htm?center_id=' + centerId + '&phone='+phone + ' #update');
//                 }
//                 else
//                 {
//                     //display toast error
//                 }
//             }
//              
//              $('#update').load('enquiry_search.htm #update');
//          });
//          
//         
//         $('.menu').click(function(){
//             $('.item').slideDown('slow');
//         });
//         
//         $('#course_setup').click(function(){
//             
//             
////             $('.input_edit').hide();
//             
//             $('#update').load('course_setup.htm #update');
////             alert('no');
//             
////              $('.input_edit').val('yes');
////              $('.input_edit').hide();
//             
////             $('.input_edit').live('onblur', function(){
////                 
////                 alert('Enter');
////                 $('.input_edit').hide();
////                 
////             });
//             
//             
//             
//             
//         });
//         
//         
//         $('#course_info').click(function(){
//             
//             $('#update').load('course_info.htm #update');
//
//             
//         });
//         
//         $('#staff_reg_setup').click(function(){
//             
//         
//             $('#update').load('staff_reg_setup.htm #update');
//
//             
//         });
//         
////         $('.input_edit').live('focus', function(){
////                 
////                 alert('En');
////                 $('.input_edit').show();
////                 
////             });
//         
//         $('#center_setup').click(function(){
//             
//             
//             $('#update').load('center_setup.htm #update');
//             
//         });
//         
//         $('#add').click(function(){
//             
////             alert('Yea..');
//             
//         });
//         
//         setInterval(function() {
//            
//            var dt = new Date();
//            var t;
//            var time;
//            if(dt.getHours()>12)
//            {
//                t = dt.getHours()-12;
//                time = t + ":" + dt.getMinutes() + ":" + dt.getSeconds() + "pm";
//            }
//            else
//            {
//                time = dt.getHours() + ":" + dt.getMinutes() + ":" + dt.getSeconds() + "am";
//            }
// 
//            document.getElementById('time').innerHTML = 'Time: '+time;
//            
//        }, 1000);
////         $('#update').delegate('.stu_group', 'click', function()
//         $("#update").delegate("#course_event", 'change', function(){
//             
//             var course = $('#course_event').val();
//             
//             
//             $('#payment').val("");
//             $('#balance').val("");
//             
//             $('#bill').load('get_billing.htm?course_id=' + course + ' #bill');
//             
////             $.ajax({
////                type: 'GET',
////                url: 'get_billing.htm',
////                data: { "course_id": course },
////                success: function(data) {
////                // grabbed some data!
////                
////                alert(data);  
////                
////                },
////                error: function()
////                {
////                    alert(fail);
////                }
////                });
//             
//             
////             $('#course_event').load('get_billing.htm?course='+$(this).attr('id') + ' #update');
//             //$('#billing').val('80000');
//             
//             
//         });
//         
//         $("#update").delegate("#pay", 'change', function(){
//             
////             alert("ok");
//             var billing = parseFloat($('#billing').val());
//             
//             var payment = parseFloat($('#pay').val());
//             
//             var diff = billing - payment;
////             $('#payment').val(diff);
//             
//             
//             $('#bal').val(diff);
//             
//             
//         });
//         
//         
//         $("#update").delegate("#stuRegFrm", 'submit', function(){
//             
//             alert("ok");
//             var fname = $('#fname').val();
//             var lname = $('#lname').val();
//             var email = $('#email').val();
//             var center = $('#center').val();
//
//             var bill = $('#billing').val();
//             alert(bill);
//             alert(new String(bill).trim());
//             var pay = $('#pay').val();
//             var bal = $('#bal').val();
//             var course = $('#course_event').val();
//             var addr = $('#addr').val();
//             var phone = $('#phone').val();
//             var tellerNo =$('#teller_no').val();
//             var batch = $('#batch').val();
//             
//             
//             var fnameElt = $('#fname');
//             var lnameElt = $('#lname');
//             var emailElt = $('#email');
////             var centerElt = $('#center');
//
//             var billElt = $('#billing');
//             
//             
//             var payElt = $('#pay');
//             var balElt = $('#bal');
////             var courseElt = $('#course_event');
//             var addrElt = $('#addr');
//             var phoneElt = $('#phone');
//             var tellerNoElt =$('#teller_no');
////             var batchElt = $('#batch').val();
//             
//             
//             alert("now validate");
//             if(new String(fname).trim().length!=0 || new String(lname).trim().length!=0 || new String(email).trim().length!=0 || new String(center).trim().length!=0 || new String(tellerNo).trim().length!=0 || new String(addr).trim().length!=0 || new String(course).trim().length!=0 || new String(bill).trim().length!=0 || new String(pay).trim().length!=0 || new String(bal).trim().length!=0 || new String(addr).trim().length!=0 || new String(phone).trim().length!=0 || new String(batch).trim().length!=0)
//              {
////                  alert('Complete it');
////              }
////              else
////              {
//                  alert('Complete alreadyY');
////                  $('#update').load('register_stu.htm #update');
//                  $.ajax(
//                          {
//                              url: "register_new_student.htm",
//                              type: "POST",
//                              data: $(this).serialize(),
//                              success: function (data) {
//                            alert(data);
//                            
//                            alert('Successful');
//                            
//                            fnameElt.val("");
//                            lnameElt.val("");
//                            emailElt.val("");
//                            billElt.val("");
//                            payElt.val("");
//                            balElt.val("");
//                            addrElt.val("");
//                            phoneElt.val("");
//                            tellerNoElt.val("");
//                            
//                        }
//                              
//                          });
//              }
//             
////             $('#payment').val(diff);
//            
//             
////             $('#balance').val(diff);
//             
//             return false;
//             
//         });
//         
//         
//         
//         
//         
//         
//         
//         
//         $("#update").delegate("#saveBatchFrm", 'submit', function(){
//             
//             var batchCode = $('#batch_code').val();
//             var batchName = $('#batch_name').val();
//             var course = $('#course').val();
//             var batchStatus = $('#batch_status').val();
//
//             var startDate = $('#start_date').val();
//             
//             
//             var endDate = $('#end_date').val();
//             var instr = $('#instr').val();
//             var center = $('#center').val();
//             
////             if(new String(batchCode).trim().length!=0 && new String(batchName).trim().length!=0 && new String(course).trim().length!=0 && new String(batchStatus).trim().length!=0 && new String(startDate).trim().length!=0 && new String(endDate).trim().length!=0 && new String(course).trim().length!=0 && new String(instr).trim().length!=0 && new String(center).trim().length!=0)
//              if(new String(batchCode).trim().length!=0 && new String(batchName).trim().length!=0 && new String(course).trim().length!=0 && new String(batchStatus).trim().length!=0 && new String(startDate).trim().length!=0 && new String(endDate).trim().length!=0 && new String(course).trim().length!=0 && new String(center).trim().length!=0)
//              {
//
//                  $.ajax(
//                          {
//                             url: "save_batch_frm.htm",
//                              type: "POST",
//                              data: $(this).serialize(),
//                              success: function (data) {
//                            $('#update').html(data);
//                            
//                        }
//                              
//                          });
//              }
//              else
//              {
//                  alert('complete form');
//              }
//
//             return false;
//             
//         });
//         
//         $("#update").delegate("#editBatchFrm", 'submit', function(){
//             
//             
//             var batchCode = $('#batch_code').val();
//             var batchName = $('#batch_name').val();
//             var course = $('#course').val();
//             var batchStatus = $('#batch_status').val();
//
//             var startDate = $('#start_date').val();
//             
//             
//             var endDate = $('#end_date').val();
//             var instr = $('#instr').val();
//             var center = $('#center').val();
//             
//             if(new String(batchCode).trim().length!=0 && new String(batchName).trim().length!=0 && new String(course).trim().length!=0 && new String(batchStatus).trim().length!=0 && new String(startDate).trim().length!=0 && new String(endDate).trim().length!=0 && new String(course).trim().length!=0 && new String(instr).trim().length!=0 && new String(center).trim().length!=0)
//              {
//
//                  $.ajax(
//                          {
//                              url: "edit_batch_frm.htm",
//                              type: "POST",
//                              data: $(this).serialize(),
//                              success: function (data) {
//
//                            $('#update').html(data);
//                            
//                        }
//                              
//                          });
//              }
//
//             return false;
//             
//         });
//         
//         $("#update").delegate("#search_bt", 'click', function(){
//
//
//                var centerId = $('#center_idd').val();
//                 if($('#email').is(':checked'))
//                 {
//                     //alert('checked');
//                     var email = $('#stu_find').val();
//                     
//                     if(email!="")
//                     {
////                        $('#update').load('find_stu_by_email.htm?centerId=' + centerId + '&email='+email + ' #update');
//                            $('#update').load('find_stu_by_email2.htm?centerId=' + centerId + '&email='+email + ' #update');
//                     }
//                     else
//                     {
//                         //display toast error
//                     }
//                     
//                 }
//                 else if($('#phone').is(':checked'))
//                 {
//                     //alert('checked');
//                     var phone = $('#stu_find').val();
//                     if(phone!="")
//                     {
//                         
////                        $('#update').load('find_stu_by_phone.htm?centerId=' + centerId + '&phone='+phone + ' #update');
//                        $('#update').load('find_stu_by_phone2.htm?centerId=' + centerId + '&phone='+phone + ' #update');
//                        
//                     }
//                     else
//                     {
//                         //display toast error
//                     }
//                 }
////                 else if($('#teller_no').is(':checked'))
////                 {
////                     alert('checked');
////                 }
////             }
////             else
////             {
////                 
////             }
//             return false;
//         });
//         
//         $("#update").delegate("#edit_stu_info", 'click', function(){
//             
//             var searchBy = $('#seach_by').val();
//             var centerId = $('#center_idd').val();
//             
//             
//             
//             if(searchBy=="phone")
//             {
////                 var phone = $('#ph').innerHTML;
//                 var phone = $('#phone').val();
//                 $('#update').load('find_stu_by_phone_edit.htm?centerId=' + centerId + '&phone='+phone + ' #update');
//
//                
//
//             }
//             else if(searchBy=="email")
//             {
////                 var email = $('#em').innerHTML;
//                 var email = $('#email').val();
//                 
//                 
//                 
//                 $('#update').load('find_stu_by_email_edit.htm?centerId=' + centerId + '&email='+email + ' #update');
//             }
//             
//             return false;
//         });
//         
//         
//         $("#update").delegate("#edit_stu", 'click', function(){
//             
//             
//             var id = $('#id_course').val();
//             var searchBy = $('#seach_by').val();
//             var centerId = $('#center_idd').val();
//             
//             if($('#b_i').is(':checked'))
//             {
//                if(searchBy=='email')
//                {
//                    
////                     $('#update').load('find_stu_by_email_edit_option.htm?centerId=' + centerId + '&email='+email + '&option=bi' + ' #update');
//                        $('#update').load('find_stu_by_id_edit_option.htm?centerId=' + centerId + '&id='+id + '&option=bi' + ' #update');
//                }
//                else
//                {
//                    
//                    var phone = $('#phone').val();
////                    $('#update').load('find_stu_by_phone_edit_option.htm?centerId=' + centerId + '&phone='+phone + '&option=bi' + ' #update');
//                    $('#update').load('find_stu_by_id_edit_option.htm?centerId=' + centerId + '&id='+id + '&option=bi' + ' #update');
//                }
//             }
//             else if($('#a_p').is(':checked'))
//             {
//                if(searchBy=='email')
//                {
//                    var email = $('#email').val();
////                     $('#update').load('find_stu_by_email_edit_option.htm?centerId=' + centerId + '&email='+email + '&option=ap' + ' #update');
//                     $('#update').load('find_stu_by_id_edit_option.htm?centerId=' + centerId + '&id='+id + '&option=ap' + ' #update');
//                }
//                else
//                {
//                    var phone = $('#phone').val();
////                    $('#update').load('find_stu_by_phone_edit_option.htm?centerId=' + centerId + '&phone='+phone + '&option=ap' + ' #update');
//                    $('#update').load('find_stu_by_id_edit_option.htm?centerId=' + centerId + '&id='+id + '&option=ap' + ' #update');
//                }
//             }
//             else if($('#c_c').is(':checked'))
//             {
//                if(searchBy=='email')
//                {
//                    var email = $('#email').val();
////                     $('#update').load('find_stu_by_email_edit_option.htm?centerId=' + centerId + '&email='+email + '&option=cc' + ' #update');
//                    $('#update').load('find_stu_by_id_edit_option.htm?centerId=' + centerId + '&id='+id + '&option=cc' + ' #update');
//                }
//                else
//                {
//                    var phone = $('#phone').val();
////                    $('#update').load('find_stu_by_phone_edit_option.htm?centerId=' + centerId + '&phone='+phone + '&option=cc' + ' #update');
//                    $('#update').load('find_stu_by_id_edit_option.htm?centerId=' + centerId + '&id='+id + '&option=cc' + ' #update');
//                }
//             }
//             else
//             {
//                 //display error
//             }
//            
//         });
//         
//         
//         
//         $("#role_setup").click(function(){
//             
//             $('#update').load('role_setup.htm #update');
//         });
//         
//         $("#config_setup").click(function(){
//             $('#update').load('config_list.htm #update');
//         });
//         
//         $("#update").delegate(".config_edit", 'click', function(){
//             var configId = $(this).attr("title");
//             $('#update').load('config_prepare_edit.htm?config_id=' + configId + ' #update');
//         });
//         
//         $("#update").delegate("#new_config", 'click', function(){
//             
//             alert('ready?');
////             var centerId = $('#center_idd').val();
////             var centerName = $('#center_name').val();
////             alert(centerId);
////             alert(centerName);
//             $('#update').load('new_config.htm #update');
//             
//         });
//         
//         $("#department_setup").click(function(){
//             
//             $('#update').load('department_setup.htm #update');
//         });
//         
//         $("#update").delegate(".course_edit", 'click', function(){
//             
//             alert('work on this');
//             
//         });
//         
//         $("#update").delegate("#edit_stu_bi_frm", 'submit', function(){
//             
//             
//             
//             var fname = $('#fname').val();
//             var lname = $('#lname').val();
//             var phone = $('#phone').val();
//             var email = $('#email').val();
//             var address = $('#addr').val();
//             var tellerNo = $('#teller_no').val();
//             var batch = $('#batch').val();
////             var course = $('#course').val();
////             var center = $('#center').val();
////             var comment = $('#comment').val();
//             
////             var staffId = $('#staff_id').val();
//             
////             var staffId = $('#staff_identity').val();
//             
//             if(new String(fname).trim().length!=0 || new String(lname).trim().length!=0 || new String(email).trim().length!=0 || new String(phone).trim().length!=0 || new String(tellerNo).trim().length!=0 || new String(address).trim().length!=0 || new String(batch).trim().length!=0)
//              {
//                  
//                  $.ajax(
//                          {
////                              url: "edit_stu_bi_frm.htm",
//                              url: "edit_stu_bi_frm2.htm",
//                              type: "POST",
//                              data: $(this).serialize(),
//                              success: function (data) {
//                                
//                                $('#update').html(data);
//                            }
//                              
//                          });
//              }
//             
//             
//             return false;
//         });
//         
//         $("#update").delegate(".role_delete", 'click', function(){
//             
//             
//             var roleId = $(this).attr('title');
////             alert(roleId);
//             
//             $('#update').load('role_delete.htm?role_id=' + roleId + ' #update');
//             
//         });
//         
//         $("#update").delegate("#department_setup_save", 'click', function(){
//             
//             alert('UPdate?');
//             
//             var departmentId = $('#department_id_setup').val();
//             var departmentName = $('#department_name_setup').val();
//             
//             
//             
////             $('#update').load('department_setup_save.htm?department_id=' + departmentId + '&department_name=' + encodeURIComponent(departmentName) + ' #update');
//             
//             $.ajax(
//                          {
//                              url: "department_setup_save.htm",
//                              type: "GET",
//                              data: {department_id : departmentId,
//                                    department_name : encodeURIComponent(departmentName)},
//                              success: function (data) {
//                                alert(data);
//                                
//                                $('#update').html(data);
//                            
//                            }
//                              
//                          });
//             
//         });
//         
//         $("#update").delegate(".department_delete", 'click', function(){
//             
//             
//             var departmentId = $(this).attr('title');
////             alert(roleId);
//             
//             $('#update').load('department_delete.htm?department_id=' + departmentId + ' #update');
//             
//         });
//         
//         
//         $("#update").delegate(".department_edit", 'click', function(){
//             
//             alert('work on this');
//             
//         });
//         
//         $("#update").delegate(".batch_status_delete", 'click', function(){
//             
//             
//             var batchStatusId = $(this).attr('title');
////             alert(roleId);
//             
//             $('#update').load('batch_status_delete.htm?batch_status_id=' + batchStatusId + ' #update');
//             
//         });
//         
//         $("#update").delegate(".batch_status_edit", 'click', function(){
//             
//             alert('work on this');
//             
//         });
//         
//         $("#batch_status_setup").click(function(){
//             
//             $('#update').load('batch_status_setup.htm #update');
//         });
//         
//         
//         
//         
//         $("#update").delegate("#frm_enquiry", 'submit', function(){
//             
//             var fname = $('#firstName').val();
//             var lname = $('#lastName').val();
//             var phone = $('#phone').val();
//             var email = $('#email').val();
//             var course = $('#course').val();
//             var center = $('#center').val();
//             var comment = $('#comment').val();
//             
//             
//             
//             var fnameElt = $('#firstName');
//             var lnameElt = $('#lastName');
//             var phoneElt = $('#phone');
//             var emailElt = $('#email');
//             var commentElt = $('#comment');
//             var knowHowElt = $('#know_how');
//             
////             var staffId = $('#staff_id').val();
//             
//             var staffId = $('#staff_identity').val();
//             
//             var msg = $('#msg');
//             msg.hide();
////             if(new String(fname).trim().length!=0 || new String(lname).trim().length!=0 || new String(email).trim().length!=0 || new String(center).trim().length!=0 || new String(course).trim().length!=0 || new String(phone).trim().length!=0 || new String(comment).trim().length!=0)
//                if((new String(fname).trim().length!=0 || new String(lname).trim().length!=0) && (new String(email).trim().length!=0 || new String(phone).trim().length!=0) && (new String(course).trim().length!=0 && new String(center).trim().length!=0) || new String(comment).trim().length!=0)
//              {
//                  $.ajax(
//                          {
//                              url: "save_new_enquiry.htm",
//                              type: "POST",
//                              data: $(this).serialize(),
//                              success: function (data) 
//                              {
//                                
//                                var reply = $(data).find("#reply").html();
//                                
//                               
//                               fnameElt.val("");
//                               lnameElt.val("");
//                               phoneElt.val("");
//                               emailElt.val("");
//                               knowHowElt.val("");
//                               commentElt.val("");
//                               
//                               msg.text(fname + " " + lname + " record saved");
//                               msg.show();
//                            
//                            }
//                              
//                          });
//              }
//              else
//              {
//                  alert('Complete Input');
//              }
//             
//             
//             return false;
//         });
//         
//         $("#update").delegate("#new_batch", 'click', function(){
//             
////             alert('ready?');
//             var centerId = $('#center_idd').val();
//             var centerName = $('#center_name').val();
//             $('#update').load('new_batch.htm?center_id=' + centerId + '&center_name=' + encodeURI(centerName) + ' #update');
//             
//             
//         });
//         
//         
//         
//         
//         
//         
//         
//         
//         
//         
//         
//         
//         
//         
//         
//         
//         $("#update").delegate(".course_edit", 'click', function(){
//             
////             alert('yutr');
//             var r = $(this).attr('title');
//             
//             
//             
//             alert(r+'zzxxx');
//             
//             var tr = $(this).parent().parent().get(0).tagName;
//             
//             var d = tr.child();
//             
//             alert(d);
//             
//             tr.hide();
//             
////             var s = d.find('.input_edit');
////             
////             alert(s+'>>:::');
////             
////             s.show();
//             
////             $('.input_edit').next().hide();
////             $('.input_edit').show();
//             
////             var p = $(this);
////             
////             alert(p);
////             var courseId = $('#course_id_setup').val();
////             var courseName = $('#course_name_setup').val();
////             var courseBilling = $('#course_billing_setup').val();
////             
////             
////             alert(courseId);
////             
////             $('#update').load('course_setup_save.htm?courseId=' + courseId + '&courseName=' + courseName + '&courseBilling=' + courseBilling + ' #update');
//             
//         });
//         
//         $("#update").delegate(".course_delete", 'click', function(){
//             
//             alert('yutr- delete');
//             var courseId = $(this).attr('title');
//             alert(courseId);
//        
////            $.ajax(
////                          {
////                              url: "course_delete.htm",
////                              type: "GET",
////                              data: {courseId : courseId},
////                              success: function (data) {
////                            alert(data);
////                            
////                            $('#update').html(data);
////
////                        }
////
////                          });
//             $('#update').load('course_delete.htm?courseId=' + courseId + ' #update');
//             
//         });
//         
//         
//         $("#update").delegate(".enq_delete", 'click', function(){
//             
//             alert('yutr- delete');
//             var email = $(this).attr('title');
//             
//             var centerId = $('#center_id').val();
//             var counselorId = $('#counselor_id').val();
//             
//             alert(counselorId);
//        
////            $.ajax(
////                          {
////                              url: "course_delete.htm",
////                              type: "GET",
////                              data: {courseId : courseId},
////                              success: function (data) {
////                            alert(data);
////                            
////                            $('#update').html(data);
////
////                        }
////
////                          });
//             $('#update').load('enquiry_delete.htm?email=' + email + '&center_id=' + centerId + '&counselor_id=' + counselorId + ' #update');
//             
//         });
//         
//         $("#update").delegate(".btn_course_save", 'click', function(){
//
//                var inputEdit = $(this).parent().parent().find("input.tx_edit");
//                
//                var courseName = inputEdit[1].value;
//                var courseBilling = inputEdit[2].value;              
//                
//                var courseId = $(this).attr('title');
//
//                
//                $('#update').load('course_edit.htm?course_id=' + courseId + '&course_name=' + encodeURI(courseName) + '&course_billing=' + encodeURI(courseBilling) + ' #update');
//             
//
//         });
//         
//         
//         $("#update").delegate(".btn_role_save", 'click', function(){
//
//                var inputEdit = $(this).parent().parent().find("input.tx_edit");
//                
//                var roleName = inputEdit[1].value;
////                var centerAddress = inputEdit[2].value;              
//                
//                var roleId = $(this).attr('title');
//
//                
//                $('#update').load('role_edit.htm?role_id=' + roleId + '&role_name=' + encodeURI(roleName) + ' #update');
//             
//
//         });
//         
//         $("#update").delegate(".btn_batch_status_save", 'click', function(){
//
//                var inputEdit = $(this).parent().parent().find("input.tx_edit");
//                
//                var batchStatusName = inputEdit[1].value;
////                var centerAddress = inputEdit[2].value;              
//                
//                var batchStatusId = $(this).attr('title');
//
//                
//                $('#update').load('batch_status_edit.htm?batch_status_id=' + batchStatusId + '&batch_status_name=' + encodeURI(batchStatusName) + ' #update');
//             
//
//         });
//         
//         $("#update").delegate(".btn_department_save", 'click', function(){
//
//                var inputEdit = $(this).parent().parent().find("input.tx_edit");
//                
//                var departmentName = inputEdit[1].value;
////                var centerAddress = inputEdit[2].value;              
//                
//                var departmentId = $(this).attr('title');
//
//                
//                $('#update').load('department_edit.htm?department_id=' + departmentId + '&department_name=' + encodeURI(departmentName) + ' #update');
//             
//
//         });
//         
//         $("#update").delegate(".btn_center_save", 'click', function(){
//
//                var inputEdit = $(this).parent().parent().find("input.tx_edit");
//                
//                var centerName = inputEdit[1].value;
//                var centerAddress = inputEdit[2].value;              
//                
//                var centerId = $(this).attr('title');
//
//                
//                $('#update').load('center_edit.htm?center_id=' + centerId + '&center_name=' + encodeURI(centerName) + '&center_address=' + encodeURI(centerAddress) + ' #update');
//             
//
//         });
//         
//         $("#update").delegate(".btn_cancle", 'click', function(){
//
//                var inputEdit = $(this).parent().parent().find("input.tx_edit");
//                var span = $(this).parent().parent().find("span.span_display");
//                var btnEdit = $(this).parent().parent().find("input.btn_edit");
//                var btnDelete = $(this).parent().parent().find("input.btn_delete");
//                var btnSave = $(this).parent().parent().find("input.btn_delete").next();
//                var btnCancle = $(this).parent().parent().find("input.btn_cancle");
//                
//             btnEdit.show();
//             btnDelete.show();
//             span.show();
//             
//             
//             btnSave.hide();
//             btnCancle.hide();
//             inputEdit.hide();
//
//         });
//         
//         $("#update").delegate(".btn_edit", 'click', function(){
//
//                var inputEdit = $(this).parent().parent().find("input.tx_edit");
//                var span = $(this).parent().parent().find("span.span_display");
//                var btnEdit = $(this).parent().parent().find("input.btn_edit");
//                var btnDelete = $(this).parent().parent().find("input.btn_delete");
//        
//                var btnSave = $(this).parent().parent().find("input.btn_delete").next();
//                var btnCancle = $(this).parent().parent().find("input.btn_cancle");
//                
//             btnEdit.hide();
//             btnDelete.hide();
//             btnSave.show();
//             btnCancle.show();
//             
//             span.hide();
//             inputEdit.show();
//
//         });
//         
//         $("#update").delegate(".center_delete", 'click', function(){
//             
//             alert('yutr- delete');
//             var centerId = $(this).attr('title');
//             alert(centerId);
//             
////             var courseId = $('#course_id_setup').val();
////             var courseName = $('#course_name_setup').val();
////             var courseBilling = $('#course_billing_setup').val();
////             
////             
////             alert(courseId);
////             
//             $('#update').load('center_delete.htm?centerId=' + centerId + ' #update');
//             
//         });
//         
//         $("#update").delegate(".btn_batch_edit", 'click', function(){
//
////         reset();
////
////                var inputEdit = $(this).parent().parent().find("input.tx_edit");
////                var span = $(this).parent().parent().find("span.span_display");
////                var btnEdit = $(this).parent().parent().find("input.btn_batch_edit");
////                var btnDelete = $(this).parent().parent().find("input.btn_delete");
////        
////                var btnSave = $(this).parent().parent().find("input.btn_delete").next();
////                var btnCancle = $(this).parent().parent().find("input.btn_batch_cancle");
////                
////             btnEdit.hide();
////             btnDelete.hide();
////             btnSave.show();
////             btnCancle.show();
////             
////             span.hide();
////             inputEdit.show();
////             $('#change_status').show();
//             var centerId = $('#center_idd').val();
//             var batchId = $(this).attr('title');
//             
//             $('#update').load('batch_prepare_edit.htm?batch_id=' + batchId + '&center_id=' + centerId + ' #update');
//             
//
//         });
//         
//         
////         $("#update").delegate(".btn_batch_cancle", 'click', function(){
////
////
//////         reset($(this));
////                var inputEdit = $(this).parent().parent().find("input.tx_edit");
////                var span = $(this).parent().parent().find("span.span_display");
////                var btnEdit = $(this).parent().parent().find("input.btn_batch_edit");
////                var btnDelete = $(this).parent().parent().find("input.btn_delete");
////                var btnSave = $(this).parent().parent().find("input.btn_delete").next();
////                var btnCancle = $(this).parent().parent().find("input.btn_batch_cancle");
////                
////             btnEdit.show();
////             btnDelete.show();
////             span.show();
////             
////             
////             btnSave.hide();
////             btnCancle.hide();
////             inputEdit.hide();
////             
////             $('#change_status').hide();
////
////         });
//         
////         function reset()
////         {
////             
////             $('input.tx_edit').hide();
////             $('span.span_display').show();
////             $('input.btn_batch_edit').show();
////             $('input.btn_delete').show();
////             $('input.btn_delete').next().show();
////             $('input.btn_batch_cancle').hide();
////             
////             $('input.btn_batch_save').hide();
////             
////             $('#change_status').hide();
////             
////         }
//         
//         
////         $("#update").delegate(".btn_batch_save", 'click', function(){
////
////                var inputEdit = $(this).parent().parent().find("input.tx_edit");
////                
////                var courseName = inputEdit[1].value;
////                var startDate = inputEdit[2].value;              
////                var endDate = inputEdit[3].value;     
////                var status = $('#change_status').val();     
////                
////                
////                
////                
////                var batchId = $(this).attr('title');
////
////                
////                $('#update').load('batch_edit.htm?batch_id=' + batchId + '&course_name=' + encodeURI(courseName) + '&start_date=' + encodeURI(startDate) + '&end_date=' + encodeURI(endDate) + '&status=' + encodeURI(status) + ' #update');
////             
////
////         });
//         
//         $("#update").delegate(".gly_billing_edit", 'click', function(){
//
////         var spanCloseActive = $("span.close_active");
////         defaultReset(spanCloseActive);
//
//         var inputEdit = $("input.tx_billing_edit");
//         var spanBillingDisplay = $("span.span_billing_display");
//         var spanBillingRemove = $("span.gly_billing_remove");
//         var spanBillingOk = $("span.gly_billing_ok");
//
////         inputEdit.addClass('active');
//
//         spanBillingDisplay.hide();
//         $(this).hide();
//         inputEdit.show();
//         spanBillingRemove.show();
//         spanBillingOk.show();
////         $('#change_status').show();
//         
//         
////         spanStatusRemove.addClass("close_active");
// 
//         });
//         
//         
//         $("#update").delegate(".gly_billing_remove", 'click', function()
//         {
//             var inputEdit = $("input.tx_billing_edit");
//             var spanBillingDisplay = $("span.span_billing_display");
//             var spanGlyBillingEdit = $("span.gly_billing_edit");
//             var spanGlyBillingOk = $("span.gly_billing_ok");
////             var spanStatusRemove = $("span.span_billing_remove");
//
//            $(this).hide();
//            spanGlyBillingOk.hide();
//            spanGlyBillingEdit.show();
//            inputEdit.hide();
//            inputEdit.val(spanBillingDisplay.html());
//            spanBillingDisplay.show();
//            
//         });
//         
//         $("#update").delegate(".gly_billing_ok", 'click', function()
//         {
//             
//             
//             
//             var inputEdit = $("input.tx_billing_edit");
//             var spanBillingDisplay = $("span.span_billing_display");
//             var spanBillingEdit = $("span.gly_billing_edit");
//             var spanStatusRemove = $("span.gly_billing_remove");
//             
//             var okElement = $(this);
//             
//             var id = $("#id_course").val();
//             var payment = $('#payment').val();
//
//             var newBilling = inputEdit.val();
//             
//             var newBalance = parseFloat(newBilling) - parseFloat(payment);
//
//             $.ajax(
//                          {
//                              url: "update_student_billing.htm",
//                              type: "GET",
//                              data: {id_course : id,
//                                    new_billing : newBilling,
//                                    payment : payment},
//                              success: function (data) {
//                                
//                            
////                                spanBillingDisplay.html(inputEdit.val());
//                                spanBillingDisplay.html(newBilling);
//                                spanBillingDisplay.attr("title", inputEdit.attr("title"));
//                                spanBillingDisplay.show();
//                                spanBillingEdit.show();
//
//                                okElement.hide();
//                                inputEdit.hide();
//                                spanStatusRemove.hide();
//                                
//                                $('label.ballanceDsplay').html(newBalance);
//
//                            }
//                              
//                          });
//         
//         });
//         $("#update").delegate(".gly_status_edit", 'click', function(){
//
//         var spanCloseActive = $("span.close_active");
//         defaultReset(spanCloseActive);
//         
//
//         var inputEdit = $(this).parent().parent().find("input.tx_status_edit");
//         var spanStatusDisplay = $(this).parent().parent().find("span.span_status_display");
//         var spanStatusRemove = $(this).parent().parent().find("span.span_status_remove");
//         var spanStatusOk = $(this).parent().parent().find("span.span_status_ok");
//
//         inputEdit.addClass('active');
//
//         spanStatusDisplay.hide();
//         $(this).hide();
//         inputEdit.show();
//         spanStatusRemove.show();
//         spanStatusOk.show();
//         $('#change_status').show();
//         
//         
//         spanStatusRemove.addClass("close_active");
// 
//         });
//         
//         $("#update").delegate("select.change_status_elt", 'change', function(){
//             
//             
////             please chech this interms of the id and content
//             var newStatus = $(".change_status_elt option:selected").html();
////             alert($(this + " option:selected").html());
////            alert($(".change_status_elt option:selected").html());
//             var inputEdit = $("input.active");
//             inputEdit.val(newStatus);
//             inputEdit.attr('title', $(this).val());
//         });
//         
//         $("#update").delegate(".span_status_remove", 'click', function()
//         {
//             
//             defaultReset($(this));
//             
////            var inputEdit = $(this).parent().parent().find("input.tx_status_edit");
////            var spanStatusDisplay = $(this).parent().parent().find("span.span_status_display");
////
////            var spanStatusEdit = $(this).parent().parent().find("span.gly_status_edit");
////
////            var spanStatusOk = $(this).parent().parent().find("span.span_status_ok");
////
////            inputEdit.removeClass('active');
////            inputEdit.val(spanStatusDisplay.html());
////
////            spanStatusDisplay.show();
////            spanStatusEdit.show();
////
////            $(this).hide();
////            inputEdit.hide();
////   //         spanStatusRemove.hide();
////            spanStatusOk.hide();
////            $('#change_status').hide();
//         
// 
//         });
// 
//         function defaultReset(elt)
//         {
//             var inputEdit = elt.parent().parent().find("input.tx_status_edit");
//            var spanStatusDisplay = elt.parent().parent().find("span.span_status_display");
//
//            var spanStatusEdit = elt.parent().parent().find("span.gly_status_edit");
//
//            var spanStatusOk = elt.parent().parent().find("span.span_status_ok");
//
//            inputEdit.removeClass('active');
//            inputEdit.val(spanStatusDisplay.html());
//
//            spanStatusDisplay.show();
//            spanStatusEdit.show();
//
//            elt.hide();
//            inputEdit.hide();
//            inputEdit.attr("title", spanStatusDisplay.attr("title"));
//   //         spanStatusRemove.hide();
//            spanStatusOk.hide();
//            $('#change_status').hide();
//            
//            
//            elt.removeClass("close_active");
//         }
//    
//         $("#update").delegate(".span_status_ok", 'click', function()
//         {
//             
//             var inputEdit = $(this).parent().parent().find("input.tx_status_edit");
//             var spanStatusDisplay = $(this).parent().parent().find("span.span_status_display");
//             var spanStatusEdit = $(this).parent().parent().find("span.gly_status_edit");
//             var spanStatusRemove = $(this).parent().parent().find("span.span_status_remove");
//             
//             var okElement = $(this);
//             
//             var batchId = $(this).attr('title');
//             var centerId = $('#center_idd').val();
//             var inputEditForBatchStatusId = $("input.active").attr('title');
//             
//             
//             $.ajax(
//                          {
//                              url: "student_batch_status_edit.htm",
//                              type: "GET",
//                              data: {batch_id : batchId,
//                                    batch_status_id : inputEditForBatchStatusId},
//                              success: function (data) {
//                            alert(data);
//                            
////                            
//alert('lk');
//                    
//                    spanStatusDisplay.html(inputEdit.val());
//                    spanStatusDisplay.attr("title", inputEdit.attr("title"));
//                    spanStatusDisplay.show();
//                    spanStatusEdit.show();
//                    
//                    okElement.hide();
//                    inputEdit.hide();
//                    spanStatusRemove.hide();
//                    $('#change_status').hide();
//                    
////                            var inputEdit = $('input.tx_status_edit');
//                            
////                            var inputEdit = $(this).parent().parent().find("input.tx_status_edit");
//                            
//////                            var inputEdit = $(this).parent().parent().find("input.active");
////                            var spanStatusDisplay = $(this).parent().parent().find("span.span_status_display");
////
////                            var spanStatusEdit = $(this).parent().parent().find("span.gly_status_edit");
//
////                            var spanStatusOk = $(this).parent().parent().find("span.span_status_ok");
//
//                            
////                            inputEdit.val(spanStatusDisplay.html());
//alert(inputEdit.get(0).tagName);
////                            spanStatusDisplay.html(inputEdit.val());
////
////                            spanStatusDisplay.show();
////                            spanStatusEdit.show();
////
////                            $(this).hide();
////                            inputEdit.hide();
////                   //         spanStatusRemove.hide();
//////                            spanStatusOk.hide();
////                            inputEdit.removeClass('active');
////                            $('#change_status').hide();
//
////
//                            
////                            $('#update').html(data);
////                            $('#update').load(data + ' #update');
//                            
//                        }
//                              
//                          });
////             $('#update').load('student_batch_status_edit.htm?batch_id=' + batchId + '&center_id=' + encodeURI(centerId) + ' #update');
//             
//             
////             
////            var inputEdit = $(this).parent().parent().find("input.tx_status_edit");
////            var spanStatusDisplay = $(this).parent().parent().find("span.span_status_display");
////
////            var spanStatusEdit = $(this).parent().parent().find("span.gly_status_edit");
////
////            var spanStatusOk = $(this).parent().parent().find("span.span_status_ok");
////
////            inputEdit.removeClass('active');
////            inputEdit.val(spanStatusDisplay.html());
////
////            spanStatusDisplay.show();
////            spanStatusEdit.show();
////
////            $(this).hide();
////            inputEdit.hide();
////   //         spanStatusRemove.hide();
////            spanStatusOk.hide();
////            $('#change_status').hide();
////         
// 
//         });
//         
//         
////         $('#course_setup').click(function()
////         {
//////             jQuery('')
////             
//////             jQuery('body').css('cursor: poiter');
////////             preventDefault();
//////             var course = $(this).attr('id');
//////             var centerId = $('#center_idd').val();
//////             
//////             var title = document.getElementById(course).innerHTML;
//////             $('#update').load('getstu.htm?course='+course + '&centerId='+ centerId + '&title=' + encodeURIComponent(title) + ' #update');
////
////                $('#update').load('course_setup.htm #update');
////             
////             return false;
////             
////         });
//         
//         $("#navigation").navPlugin({
//            'itemWidth': 150,
//            'itemHeight': 30,
//            'navEffect': 'slide',
//            'speed': 250
// });

         
        
    }
            
            
            
);


