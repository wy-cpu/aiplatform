/*!
 * json2vue v0.0.3
 * (c) 2018-2019 xaboy
 * Github https://github.com/xaboy/json2vue
 * Released under the MIT License.
 */
(function (global, factory) {
  typeof exports === 'object' && typeof module !== 'undefined' ? module.exports = factory(require('vue')) :
  typeof define === 'function' && define.amd ? define(['vue'], factory) :
  (global = global || self, global.json2vue = factory(global.Vue));
}(this, function (Vue) { 'use strict';

  Vue = Vue && Vue.hasOwnProperty('default') ? Vue['default'] : Vue;

  function _classCallCheck(instance, Constructor) {
    if (!(instance instanceof Constructor)) {
      throw new TypeError("Cannot call a class as a function");
    }
  }

  function _defineProperties(target, props) {
    for (var i = 0; i < props.length; i++) {
      var descriptor = props[i];
      descriptor.enumerable = descriptor.enumerable || false;
      descriptor.configurable = true;
      if ("value" in descriptor) descriptor.writable = true;
      Object.defineProperty(target, descriptor.key, descriptor);
    }
  }

  function _createClass(Constructor, protoProps, staticProps) {
    if (protoProps) _defineProperties(Constructor.prototype, protoProps);
    if (staticProps) _defineProperties(Constructor, staticProps);
    return Constructor;
  }

  function isType(arg, type) {
    return Object.prototype.toString.call(arg) === '[object ' + type + ']';
  }
  function isString(arg) {
    return isType(arg, 'String');
  }

  var Render =
  /*#__PURE__*/
  function () {
    function Render(jv, vm) {
      _classCallCheck(this, Render);

      this.jv = jv;
      this.vm = vm;
    }

    _createClass(Render, [{
      key: "render",
      value: function render() {
        var _this = this;

        //@ts-ignore
        var rule = this.vm.jv_$rule;
        if (isType(rule, 'Function')) rule = rule.call(this.vm);
        return Array.isArray(rule) ? this.vm.$createElement('div', {}, rule.map(function (rule) {
          return _this.renderRule(rule);
        })) : this.renderRule(rule);
      }
    }, {
      key: "renderRule",
      value: function renderRule(rule) {
        if (rule.type === "template" && rule.template) {
          return this.renderTemplate(rule);
        } else {
          var children = this.renderChildren(rule.children || []);
          return this.vm.$createElement(rule.type, getProps(rule), children);
        }
      }
    }, {
      key: "renderChildren",
      value: function renderChildren(rule) {
        var _this2 = this;

        return rule.map(function (child) {
          return isString(child) ? child : _this2.renderRule(child);
        });
      }
    }, {
      key: "renderTemplate",
      value: function renderTemplate(rule) {
        if (Vue.compile === undefined) {
          console.error("使用的 Vue 版本不支持 compile");
          return [];
        }

        if (!rule.vm) rule.vm = new Vue();
        setTemplateProps(rule); //@ts-ignore

        var vn = Vue.compile(rule.template).render.call(rule.vm);
        return vn;
      }
    }]);

    return Render;
  }();
  var propsName = ['class', 'style', 'attrs', 'props', 'domProps', 'on', 'nativeOn', 'directives', 'scopedSlots', 'slot', 'ref', 'key'];

  function getProps(rule) {
    var props = {};
    propsName.forEach(function (name) {
      if (rule[name] !== undefined) props[name] = rule[name];
    });
    return props;
  }

  function setTemplateProps(rule) {
    if (!rule.vm.$props || !rule.props) return;
    var keys = Object.keys(rule.vm.$props);
    keys.forEach(function (key) {
      if (rule.props[key] !== undefined) rule.vm.$props[key] = rule.props[key];
    });
  }

  function component(jv) {
    var jsonRender;
    return {
      mixins: [jv.config],
      data: function data() {
        return {
          jv_$rule: jv.config.rule
        };
      },
      beforeCreate: function beforeCreate() {
        jsonRender = new Render(jv, this);
      },
      render: function render() {
        return jsonRender.render();
      }
    };
  }

  var JsonVue =
  /*#__PURE__*/
  function () {
    function JsonVue(config) {
      _classCallCheck(this, JsonVue);

      this.config = config;
      this.component = component(this);
    }

    _createClass(JsonVue, [{
      key: "mount",
      value: function mount(parent) {
        var $vm = new (Vue.extend(this.component))();
        $vm.$mount();
        if (isString(parent)) parent = document.querySelector(parent);
        if (parent === undefined) parent = document.body;
        parent.append($vm.$el);
        return $vm;
      }
    }]);

    return JsonVue;
  }(); //@ts-ignore
  JsonVue.version = "0.0.3";

  if (typeof window !== 'undefined') //@ts-ignore
    window['jsonVue'] = JsonVue;

  return JsonVue;

}));
