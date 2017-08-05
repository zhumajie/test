package rapid;

import cn.org.rapid_framework.generator.GeneratorFacade;

public class RapidGenerateFiles {
     public static void main(String[] args) throws Exception{
         /**
          * 用法:例子如下
          *
          * 0.首先修改generator.xml,修改为当前正确的信息(比如数据库用户名/密码,实体类名,作者姓名,生成目录...)
          * 1.修改以下代码中的表名(如:np_work_type)
          * 2.右击 Run,等待成功
          *
          * ☆☆☆☆☆☆☆☆根据情况,挑选对自己有用的代码或sql或文件,按照规范调整目录结构,删除多生成的代码与文件☆☆☆☆☆☆☆☆
          */
         GeneratorFacade g = new GeneratorFacade();
         g.getGenerator().addTemplateRootDir("kstore-code-generator\\template");
         //通过数据库表生成文件,template为模板的根目录
         g.generateByTable ("np_goods");
     }
}
