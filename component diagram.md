# Implementation View

## **Overview**

This is the implementation view of the “4+1” model view of architecture, which belongs to the architectural design of our team “SC-Platypus”.

Next, we will introduce you to the participants of this document, a UML component diagram of this project and the justification for it.

## **Participants**

| **Name** | **Roles in this view** |
| --- | --- |
| Lingrui Liang   Nianran Pan | Component diagram editor and documentation editor |
| Yi Xu | Reviewer |

<img src="component diagram.jpg">

## 1. **Justification**

This component diagram highlights modularity and security. By distinguishing roles and concentrating core functions, the system has good maintainability and scalability.

• A unified login component ensures that all users authenticate through a interface, and the separation between users ensures that they can only access information related to their roles to enhance security.

• The project has different users like student, teacher, lecturer, coordinator, we focus on the interface relationship between each user and system functions to ensure their respective functions are realized. Concentrating these functions in one system ensures efficient and consistent management of data across different components.

• For user login, we use authentication service to verify the identity of users, considering that the system contains the personal information of many students.

• The database is used when the system needs to access data.
