package abac_blockchain

import abac_blockchain.abac.ABACAlgorithm
import abac_blockchain.abac.ABACP
import abac_blockchain.attributes.AttributeEnvironment
import abac_blockchain.attributes.AttributeObject
import abac_blockchain.attributes.AttributePermission
import abac_blockchain.attributes.AttributeSubject

fun main(){
    while(true){
        println(
            """
                1. add Block
                2. delete Block
            """.trimIndent()
        )
        when(readLine()){
            "1" -> {
                println("${"-".repeat(5)}Add ABAC To Blockchain${"-".repeat(5)}\n\n")
                println("${"-".repeat(4)}Attribute Subject${"-".repeat(4)}")
                print("\nuserId = ")
                val userId = readLine()!!.toInt()
                print("\nrole = ")
                val role = readLine()
                print("\ngroup = ")
                val group = readLine()
                println("${"-".repeat(4)}Attribute Object${"-".repeat(4)}")
                print("\ndeviceId = ")
                val deviceId = readLine()
                println("${"-".repeat(4)}Attribute Permission${"-".repeat(4)}")
                print("\npermission (TRUE | FALSE) = ")
                val permission = readLine()
                println("${"-".repeat(4)}Attribute Environment${"-".repeat(4)}")
                print("\ncreate time = ")
                val createTime = readLine()!!.toLongOrNull()
                    print("\nend time = ")
                    val endTime:Long? = readLine()!!.toLongOrNull()
                    ABACAlgorithm.addPolicy(
                        ABACP(
                            AttributeSubject(
                                userId,
                                role,
                                group
                            ),
                            AttributeObject(
                                deviceId
                            ),
                            if (permission.isNullOrEmpty())
                                null
                            else if (permission.toLowerCase() == ("true"))
                                AttributePermission.ALLOW
                            else
                                AttributePermission.DENY
                            ,
                            AttributeEnvironment(
                                createTime,
                                endTime
                            )
                        )
                    )
                }
            "2" -> {
                println("${"-".repeat(5)}Delete ABAC From Blockchain${"-".repeat(5)}\n\n")
                println("${"-".repeat(4)}Attribute Subject${"-".repeat(4)}")
                print("\nuserId = ")
                val userId = readLine()!!.toInt()
                print("\nrole = ")
                val role = readLine()
                print("\ngroup = ")
                val group = readLine()
                println("${"-".repeat(4)}Attribute Object${"-".repeat(4)}")
                print("\ndeviceId = ")
                val deviceId = readLine()
                ABACAlgorithm.deletePolicy(
                    AttributeSubject(
                        userId,role,group
                    ),
                    AttributeObject(
                        deviceId
                    )
                )
            }
            else ->
                continue
        }
    }
}