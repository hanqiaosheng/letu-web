
   UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
   UE.Editor.prototype.getActionUrl = function(action) {
   	   //判断路径   这里是config.json 中设置执行上传的action名称
       if (action == 'uploadimage') {
           return 'http://localhost/sunny_bike_consumer_cms/'+'cms/upload/uploadImage.action';
       } else if (action == 'uploadvideo') {
           return '';
       } else {
           return this._bkGetActionUrl.call(this, action);
       }
   }