Object.extend=function(t,e){for(var i in e){t[i]=e[i]}return t};window.lib=window.lib||{};if(!lib.SITE_DOMAIN){var getDomain=function(){var t=2;var e=window.location.hostname.split(".");e=e.slice(e.length-t);return e.join(".")};lib.SITE_DOMAIN=getDomain()}lib.PROJECT_VERSION="201104221818300317";lib.action=lib.action||{};lib.action.Qa=function(){this.init=function(t){var e=this;var i=lib.SITE_DOMAIN.match(/pps/);try{var r;var n=navigator.userAgent.toLowerCase();this.par={};this.pars=[];this.custom={};this.filter=[];this.time=0;this.w=window;this.l=window.location;this.d=window.document;this.urlMap={rdm:"rdm",qtcurl:"qtcurl",rfr:"rfr",lrfr:"lrfr",jsuid:"jsuid",qtsid:"qtsid",ppuid:"ppuid",platform:"platform",weid:"weid",pru:"pru",flshuid:"flshuid",fcode:"fcode",ffcode:"ffcode",coop:"coop",odfrm:"odfrm"};this.cookieMap={flshuid:"QC005",jsuid:"QC006",pru:"P00PRU",lrfr:"QC007",qtsid:"QC008",QY_FC:"QC009",QY_FFC:"QC014",gaflag:"QC011",odfrm:"QC132"};t=t||{};this.times=t.times||5;this.timeouts=t.timeouts||1e3;this.url=t.url||window.location.protocol+"//msg.71.am/jspb.gif";if(this.url.indexOf("?")==-1){this.url+="?"}else if(this.url.slice(-1)!="&"){this.url+="&"}this.flag=t.flag||"QC010";this.callback=t.callback||function(){};if(typeof t.urlMap=="object"){Object.extend(this.urlMap,t.urlMap)}if(typeof t.cookieMap=="object"){Object.extend(this.cookieMap,t.cookieMap)}if(typeof t.custom=="object"){Object.extend(this.custom,t.custom)}if(t.filter instanceof Array){this.filter=t.filter}var o=this.urlMap;this.par[o.rdm]=this.rand();this.par[o.qtcurl]=this.u(this.l.href);this.par[o.rfr]=this.u(this.d.referrer);this.par[o.lrfr]=this.getLrfr();this.par[o.jsuid]=this.getJsuid();this.par[o.qtsid]=this.getQtsid();this.par[o.ppuid]=this.getUserInfoUid();this.par[o.platform]=/ipad/i.test(n)?"21":/iphone os/i.test(n)?"31":"11";if(i){this.par[o.platform]="20"+this.par[o.platform]}this.par[o.fcode]=this.getFc();this.par[o.ffcode]=this.getFfc();this.par[o.coop]=this.getCoop();this.par[o.weid]=this.getWeid();this.par[o.pru]=this.getPRU();Object.extend(this.par,this.custom);r=setTimeout(function(){var t=navigator.userAgent.toLowerCase();var i=/ipad/i.test(t)||/iphone os/i.test(t)||/lepad_hls/i.test(t);var n;if(lib.qa_postServerUID||e.time>=e.times||i){e.par[o.flshuid]=e.setFlashId(lib.qa_postServerUID||"")||e.cookieGet("QC005");e.post();n=true}else{e.time+=1;n=false}if(n===false){r=setTimeout(arguments.callee,e.timeouts);return}},0);var s=this.queryToJson(this.l.href);var a=this.cookieMap[this.urlMap.odfrm];var f=s[this.urlMap.odfrm]||this.cookieGet(a)||"";if(f){f=f;this.par[o.odfrm]=f;this.cookieSet(a,f,0,"/",lib.SITE_DOMAIN);var c=this.d.getElementsByTagName("body")[0];var u=this.queryToJson(c.getAttribute("data-pb")||"")||{};u[o.odfrm]=f;var h=this.jsonToQuery(u);c.setAttribute("data-pb",h)}var l=document.getElementById("block-B");if(l&&l.getAttribute("data-pb")){var d=l.getAttribute("data-pb");var p=d.match(/(^|&)?tmplt=([^&]+)/i);if(p&&p[2]){e.par["tmplt"]=p[2]}}}catch(v){}};this.getUserInfoUid=function(){try{var userInfo=this.cookieGet("P00002");if(userInfo){userInfo=userInfo==window.JSON?window.JSON.parse(userInfo):eval("("+userInfo+")")}if(userInfo&&userInfo.uid){return userInfo.uid}else{return""}}catch(e){return""}};this.u=function(t){try{var e=encodeURIComponent;return e instanceof Function?e(t):escape(t)}catch(i){return""}};this.hash=function(t){try{var e=1,i=0;if(t){e=0;for(var r=t.length-1;r>=0;r--){i=t.charCodeAt(r);e=(e<<6&268435455)+i+(i<<14);i=e&266338304;e=i!==0?e^i>>21:e}}return e}catch(n){return""}};this.rand=function(t){try{var e=[];if(!isNaN(t)){for(var i=0;i<t;i++){e.push(Math.round(Math.random()*2147483647).toString(36))}}else{e.push(Math.round(Math.random()*2147483647))}return e.join("")}catch(r){return""}};this.cookieGet=function(t){var e=function(t){if(new RegExp('^[^\\x00-\\x20\\x7f\\(\\)<>@,;:\\\\\\"\\[\\]\\?=\\{\\}\\/\\u0080-\\uffff]+$').test(t)){var e=new RegExp("(^| )"+t+"=([^;]*)(;|$)"),i=e.exec(document.cookie);if(i){return i[2]||""}}return""};try{t=e(t);if("string"==typeof t){if(t.length>1&&t=="deleted"){return""}else{return decodeURIComponent(t)||""}}else{return""}}catch(i){return""}};this.cookieSet=function(t,e,i,r,n,o){try{var s=[];s.push(t+"="+encodeURIComponent(e));if(i){var a=new Date;var f=a.getTime()+i*36e5;a.setTime(f);s.push("expires="+a.toGMTString())}if(r){s.push("path="+r)}if(n){s.push("domain="+n)}if(o){s.push(o)}document.cookie=s.join(";")}catch(c){return""}};this.getJsuid=function(){try{var t;var e=this.cookieMap.jsuid;t=this.cookieGet(e);if(!t||!isNaN(t)){t=this.rand(4)}this.cookieSet(e,t,365*24,"/",lib.SITE_DOMAIN);return t}catch(i){return""}};this.getQtsid=function(){try{var t,e=/^\d{10}\.\d{10}\.\d{10}\.\d+$/;var i=this.cookieMap.qtsid;var r=function(){return parseInt(new Date/1e3,10).toString()};t=this.cookieGet(i);if(this.cookieGet(this.flag)){return t}if(!e.test(t)){var n=r();t=[n,n,n,"1"]}else{t=t.split(".");t[1]=t[2];t[2]=r();t[3]=parseInt(t[3],10)+1}this.cookieSet(i,t.join("."),365*24,"/",lib.SITE_DOMAIN);return t}catch(o){return""}};this.getLrfr=function(){try{var t,e=this;var i=this.cookieMap.lrfr;var r=this.d.referrer.match(/http[s]?:\/\/([^\/]*)/);r=r?r[1]:"";t=this.cookieGet(i);t=t=="undefined"?"":t;var n=this.l.hostname;var o=r&&r.match(/i?qiyi\.com|pps\.tv/);var s=t;if(!t){if(!this.d.referrer||o){s="DIRECT"}else{s=this.u(this.d.referrer)}}else if(!this.d.referrer){s="DIRECT"}else if(r!==n&&r.indexOf(lib.SITE_DOMAIN)===-1){if(!o){s=this.u(this.d.referrer)}}this.cookieSet(i,s,0,"/",lib.SITE_DOMAIN);if(t!=s){}return s}catch(a){return""}};this.setFlashId=function(t){var e=this.cookieMap.flshuid;var i=this.cookieGet(e);if(t&&t!=i){this.cookieSet(e,t,365*24,"/",lib.SITE_DOMAIN)}return t};this.getFc=function(){try{var t=this.l.search.match(/[\?&]fc=([^&]*)(&|$)/i);var e=this.cookieMap.QY_FC;var i=this.cookieGet(e);if(i=="b22dab601821a896"){return i}if(t){t=t[1];this.cookieSet(e,t,0,"/",lib.SITE_DOMAIN)}else{t=this.cookieGet(e);if(!t||t=="undefined"){t=""}}return t}catch(r){return""}};this.getFfc=function(){try{var t=this.l.search.match(/[\?&]ffc=([^&]*)(&|$)/i);var e=this.cookieMap.QY_FFC;if(t){t=t[1];this.cookieSet(e,t,0,"/",lib.SITE_DOMAIN)}else{t=this.cookieGet(e);if(!t||t=="undefined"){t=""}}return t}catch(i){return""}};this.getCoop=function(){var t="";var e;if(this.l.host.split(".")[0]=="mini"){e=lib.$url(this.l.href,"app");e=e&&e["app"]||"";if(e){t="coop_"+e.replace("bdbrowser","bdexplorer")}}else{if(this.w.INFO&&this.w.INFO.flashVars&&this.w.INFO.flashVars.coop){t=this.w.INFO.flashVars.coop}}return t};this.getWeid=function(){return window.webEventID||""};this.getPRU=function(){return this.cookieGet("P00PRU")||""};this.post=function(){var t=this;try{t.pars=[];var e,i=t.filter.length,r;if(i===0){for(e in t.par){t.pars.push([e,t.par[e]].join("="))}}else{for(e=0;e<i;e++){r=t.filter[e];t.pars.push([r,t.par[r]].join("="))}}t.pars=t.pars.join("&");window.jsQa=new Image(1,1);window.jsQa.src=t.url+t.pars;t.cookieSet(t.flag,t.hash(t.pars),0,"/",lib.SITE_DOMAIN);t.callback()}catch(n){return""}};this.iframeRequest=function(t){var e=document.createElement("iframe");e.scrolling="no";e.style.display="none";e.frameborder=0;e.src=t;document.body.appendChild(e)};this.syncCookie=function(t,e,i){var r=this;var n;if(t.indexOf("iqiyi.com")!==-1){n="http://passport.pps.tv/pages/user/proxy.action"}else if(t.indexOf("pps.tv")!==-1){n="http://passport.iqiyi.com/pages/user/proxy.action"}if(n){setTimeout(function(){var t=n+"#"+e+"="+i;try{window.JSHandler.logToConsole("xxx")}catch(o){r.iframeRequest(t)}},0)}};this.queryToJson=function(t){var e=Array.isArray||function(t){return Object.prototype.toString.call(t)=="[object Array]"};t=t||this.l.href;var i=t.substr(t.lastIndexOf("?")+1),r=i.split("&"),n=r.length,o={},s=0,a,f,c,u;for(;s<n;s++){if(!r[s]){continue}u=r[s].split("=");a=u.shift();f=u.join("=");c=o[a];if("undefined"==typeof c){o[a]=f}else if(e(c)){c.push(f)}else{o[a]=[c,f]}}return o};this.jsonToQuery=function(t,e){var i=Array.isArray||function(t){return Object.prototype.toString.call(t)=="[object Array]"};var r=function(t,e){var i,r,n;if("function"==typeof e){for(r in t){if(t.hasOwnProperty(r)){n=t[r];i=e.call(t,n,r);if(i===false){break}}}}return t};var n=function(t){return String(t).replace(/[#%&+=\/\\\ \u3000\f\r\n\t]/g,function(t){return"%"+(256+t.charCodeAt()).toString(16).substring(1).toUpperCase()})};var o=[],s,a=e||function(t){return n(t)};r(t,function(t,e){if(i(t)){s=t.length;while(s--){o.push(e+"="+a(t[s],e))}}else{o.push(e+"="+a(t,e))}});return o.join("&")}};(function(){var t=new lib.action.Qa;try{t.init({url:window.location.protocol+"//msg.71.am/jpb.gif",times:-1})}catch(e){}})();(function(){var t=function(){var t=2;var e=window.location.hostname.split(".");e=e.slice(e.length-t);return e.join(".")}();var e=t.match(/pps/);var i=navigator.userAgent.toLowerCase();var r="1",n="10",o="101";if(/(android)|(like mac os x)/i.test(i)){r="2";n="20"}else if(e){r="201"}if(/(android)/i.test(i)){o="201"}else if(/(like mac os x)/i.test(i)){if(/(iphone)/i.test(i)){o="201"}else{o="202"}}var a="//msg.71.am/b?t=20&p=10&p1=101"+("&pf="+r);if(window.uniqy){a="//msg.71.am/b?t=20&p="+n+"&p1="+o+("&pf="+r)}var s=function(t,e){t=t||{};if(e.indexOf("?")==-1){e+="?"}else{e+="&"}var i=+new Date;t._=i;for(var r in t){if(t.hasOwnProperty(r)){e+=encodeURIComponent(r)+"="+encodeURIComponent(t[r])+"&"}}if(e[e.length-1]==="&"){e=e.slice(0,-1)}var n=new Image;n.src=e};if(!document.querySelectorAll){var f=function(t){if(!t){return}var e=[];var i=document;var r=i.getElementsByTagName("*")||i.all;for(var n=0,o=r.length;n<o;n++){if(r[n].id==t){e.push(r[n])}}return e}}function c(){var t=[];var e="block-";var i,r,n,o;var a="A".charCodeAt();var s=function(t){return document.getElementById(t)};var c=String.fromCharCode;var u;for(u=0;u<26;u++){i=c(a+u);n=e+i;var h="*[id="+n+"]";var l="";if(document.querySelectorAll){l=document.querySelectorAll(h)}else{l=f(n)}var d=l.length;if(d){if(d>1){var p=0;var v=1;while(p<d){o=l[p];if(p){o["__bid__"]=i+v;o["id"]=n+v;v++}else{o["__bid__"]=i}t.push(o);p++}}else{o=s(n);o["__bid__"]=i;t.push(o)}}}for(u=0;u<26;u++){r=c(a+u);var m=false;for(var g=0;g<26;g++){i=c(a+g);n=e+r+i;o=s(n);if(o){m=true;o["__bid__"]=r+i;t.push(o)}}}return t}function u(){var t={};var e=[];var i="block-";var r=document.getElementsByTagName("qchunk");var n,o;for(var a=0,s=r.length;a<s;a++){n=r[a];o=n.getAttribute("data-id")||"";if(o.substr(0,i.length).toLowerCase()==i){var f=o.substr(i.length);if(!t[f]){t[f]=1}else{var c=++t[f];var u;do{u=f[0];u=u+c;c++}while(t[u]);t[u]=1;n.setAttribute("data-id",u);f=u}n["__bid__"]=f;e.push(n)}}return e}function h(t,e,i){if(t._clickMapPBSent){return false}t._clickMapPBSent=true;if(e===i){e._c=1;return true}if(e._c>=1){e._c++;return false}else{if(typeof e._c!=="number"){e._c=1}else{e._c++}if(!e._adjustClickMap){var r=function(){this._c=0};e._adjustClickMap=r.bind(e);try{if(e.addEventListener){e.addEventListener("mousedown",e._adjustClickMap,false)}else{e.attachEvent("onmousedown",e._adjustClickMap)}}catch(n){}}}return true}var l=function(t){t=t||window.event;var e=t.target||t.srcElement;var i=t.currentTarget||this;if(!h(t,e,i)){return}var r=document.documentElement&&document.documentElement.scrollTop||document.body.scrollTop;var n=document.documentElement&&document.documentElement.scrollLeft||document.body.scrollLeft;var o=document.documentElement&&document.documentElement.scrollWidth||document.body.scrollWidth;var f=document.documentElement&&document.documentElement.scrollHeight||document.body.scrollHeight;var c=document.documentElement&&document.documentElement.clientHeight||document.body.clientHeight;var u=document.documentElement&&document.documentElement.clientWidth||document.body.clientWidth;var l=Math.max(f,c);var d=Math.max(o,u);var p=this["__bid__"]||"";var v=k(document.getElementsByTagName("body")[0].getAttribute("data-pb"),"&");var m=k(this.getAttribute("data-pb"),"&");var g,w,b;do{g=e;w=g.getAttribute("rseat");b=g.tagName.toUpperCase();e=e.parentNode;if(g==this){break}}while(!w&&b!=="A"&&b!=="IMG");var y=k(g.getAttribute("data-pb"),"&");var _,I,A;if(w){y.rseat=w}if(b==="A"){_=g.title||"";I="a";A=g.getAttribute("href")||""}else if(b==="IMG"){_=g.alt||"";I="i";A=""}else{_=g.title||"";I="e";A=""}var j,M,S,O;O=k(document.cookie,";");j=O["P00003"]||"";M=O["QC005"]||"";S=O["QC006"]||"";var E=window.Q&&Q.PageInfo||{};if(window.uniqy){E=window.uniqy&&uniqy.PageInfo||{}}var T=E.i18n==="tw_t"?false:true;var N;if(T){N="cn_s"}else{N="tw_t"}var x={block:p,rt:I,r:_,rlink:A,pu:decodeURIComponent(j),u:decodeURIComponent(M),jsuid:decodeURIComponent(S),ce:window.webEventID||"",re:d+"*"+l,clkx:t.clientX+n,clky:t.clientY+r,mod:N,tm:window.__qlt&&window.__qlt.statisticsStart?new Date-window.__qlt.statisticsStart:""};if(window.pingbackVersion){x.v=window.pingbackVersion}if(window.Q&&Q.PageInfo.playPageInfo&&Q.PageInfo.playPageInfo.videoTemplate){x.tmplt=Q.PageInfo.playPageInfo.videoTemplate||""}s(C(x,v,m,y),a)};var d=c();var p=u();var v,m;for(var g=0,w=d.length;g<w;g++){if(p.indexOf(d[g])==-1){p.push(d[g])}}var b=document.getElementsByTagName("body")[0];b.__bid__="body";p.push(b);var y=function(t){var e=p||[];if(t&&t.data){e=t.data.down("[data-block-name]")||[];if(e.length===0){return}var i="block-";e.forEach(function(t){t["__bid__"]=t.id.substr(i.length)})}for(var r=0,n=e.length;r<n;r++){var o=e[r];var a="";if(window.Q){a=Q(o)}else if(window.jQuery){a=$(o)}if(a.attr("data-asyn-pb")){continue}var s=l.bind(o);if(o.addEventListener){o.addEventListener("mousedown",s,false)}else{o.attachEvent("onmousedown",s)}a.attr("data-asyn-pb","true")}};var _=function(){var t=p||[];if(t.length){for(var e=0,i=t.length;e<i;e++){var r=t[e];if(r.getAttribute("data-asyn-pb")){continue}var n=l.bind(r);if(r.addEventListener){r.addEventListener("mousedown",n,false)}else{r.attachEvent("onmousedown",n)}r.setAttribute("data-asyn-pb","true")}}};if(window.Q&&Q.$){Q.$(window).on("scroll",y);Q.$(window).on("resize",y);Q.event.customEvent.on("bindingPingback",y);y()}else if(window.jQuery){try{$(window).on("scroll",y);$(window).on("resize",y);y()}catch(I){}}else{_()}function k(t,e){var i={};e=e||"&";if(t){var r=t.split(e),n;for(var o=0,a=r.length;o<a;o++){n=r[o];if(n){n=n.split(/\s*=\s*/g);if(n[0]){i[n[0].replace(/^\s*|\s*$/g,"")]=n[1]||""}}}}return i}function C(t,e){var i=t||{};var r;for(var n=1,o=arguments.length;n<o;n++){r=arguments[n];if(r){for(var a in r){if(r.hasOwnProperty(a)){i[a]=r[a]}}}}return i}})();