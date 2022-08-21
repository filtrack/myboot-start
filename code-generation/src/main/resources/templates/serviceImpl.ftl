package ${serviceImplPackage!""};

import ${servicePackage!""}.${serviceName?cap_first!""};
import ${mapperPackage!""}.${entityMapper?cap_first!""};
import org.springframework.stereotype.Service;


/**
* author ${author}
* description ${desc}
* date ${nowDate?string("yyyy-MM-dd")}
*/
@Service
public class ${className?cap_first!""} implements ${serviceName?cap_first!""} {

    final ${entityMapper?cap_first!""} ${entityMapper?uncap_first!""};
    public ${className?cap_first!""}(${entityMapper?cap_first!""} ${entityMapper?uncap_first!""}) {
        this.${entityMapper?uncap_first!""} = ${entityMapper?uncap_first!""};
    }
}

