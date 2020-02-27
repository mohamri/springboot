function clickHandler(e){
  $("#"+e.data.objId).html(e.target.id + " says " + e.data.answer +
                           " at X postion: " + e.screenX);
}
$(document).ready(function(){
  $("#div1").on({"click":clickHandler},
                {"objId":"heading", "answer":"yes"});
  $("#div2").on({"click":clickHandler},
          {"objId":"heading", "answer":"pa"});
 
});