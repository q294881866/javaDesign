/**
 * @author ppf
 * javascript继承、封装、多态的说明 
 */
/**
 * JM 命名空间 namespace
 */
var JM = {};

// 类 Person
JM.Person = function(name, sex) {
	this.name = name;
	this.sex = sex;
	this.getName = function() {
		alert(this.name);
	}
};

JM.Person.prototype = {
	constructor : JM.Person,
	msg : 'hello world!',
	hi : function() {
		alert(msg);
	}
};

// 类 Teacher
JM.Teacher = function(name, sex, level) {
	// 模板继承
	JM.Person.call(this, name, sex);
	this.level = level;
}

// 类 Student
JM.Student = function(name, sex, game) {
	// 构造函数继承模板
	JM.Student.superClass.constructor.call(this, name, sex);
	this.game = game;
};

/**
 * 实例化接口类型
 * @param name 字符串，接口名称
 * @param methods 数组，要实现的方法
 */
JM.Interface = function(name, methods) {
	// 判断接口的参数个数
	if (arguments.length != 2) {
		throw new Error('Wrong defined！');
	}
	this.name = name;
	this.methods = []; // 定义一个内置的空数组对象 等待接受methods里的元素(方法名字)
	for (var i = 0, len = methods.length; i < len; i++) {
		if (typeof methods[i] !== 'string') {
			throw new Error('The method name Illegal!');
		}
		this.methods.push(methods[i]);
	}
}

/**
 * 检查对象是否实现接口
 * @parm args 参数列表，第一个参数为对象，后面是一个或多个实现的接口
 */
JM.Interface.ensureImplements = function(args) {
	if (arguments.length < 2) {
		throw new Error('Args illegal!');
	}
	//需要实现的接口及方法
	for (var i = 1, len = arguments.length; i < len; i++) {
		var impl = arguments[i];
		if (impl.constructor !== JM.Interface) {
			throw new Error('Args illegal!');
		}
		// 每个接口对象中方法名集合
		for (var j = 0; j < impl.methods.length; j++) {
			var m = impl.methods[j];
			if (!args[m] || typeof args[m] !== 'function') {
				throw new Error("The method '" + m + "' is not defined!");
			}
		}
	}
};

/**
 * 实现继承 只继承父类的原型对象
 * 
 * @param sub
 *            子类
 * @param sup
 *            父类
 */
JM.extend = function(sub, sup) {
	// 1 临时函数
//	var F = new Function();
//	// 2 保存父类原型
//	F.prototype = sup.prototype;
//	// 3 原型继承
//	sub.prototype = new F();
	sub.prototype = sup.prototype;
	// 4 还原子类的构造器
	sub.prototype.constructor = sub;
	// 5 保存一下父类的原型对象: 一方面方便解耦 另一方面方便获得父类的原型对象
	sub.superClass = sup.prototype; // 自定义一个子类的静态属性 接受父类的原型对象
	// 判断父类的原型对象的构造器 (加保险)
	if (sup.prototype.constructor == Object.prototype.constructor) {
		// 还原父类原型对象的构造器
		sup.prototype.constructor = sup;
	}
}

/**
 * 扩展Array的原型对象 添加变量数组的每一个元素,并让每一个元素都执行fn函数 (可变量多维数组)
 * 
 * @param {Object}
 *            fn
 */
Array.prototype.each = function(fn) {
	try {
		// 1 目的： 遍历数组的每一项 //计数器 记录当前遍历的元素位置
		this.i || (this.i = 0); // var i = 0 ;
		// 2 严谨的判断什么时候去走each核心方法
		// 当数组的长度大于0的时候 && 传递的参数必须为函数
		if (this.length > 0 && fn.constructor == Function) {
			// 循环遍历数组的每一项
			while (this.i < this.length) { // while循环的范围
				// 获取数组的每一项
				var e = this[this.i];
				// 如果当前元素获取到了 并且当前元素是一个数组
				if (e && e.constructor == Array) {
					// 直接做递归操作
					e.each(fn);
				} else {
					// 如果不是数组 （那就是一个单个元素）
					// 这的目的就是为了把数组的当前元素传递给fn函数 并让函数执行
					// fn.apply(e,[e]);
					fn.call(e, e);
				}
				this.i++;
			}
			this.i = null; // 释放内存 垃圾回收机制回收变量
		}

	} catch (ex) {
		// do something
	}
	return this;
}
