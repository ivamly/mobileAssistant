import {
  useEditSpeeckerNamesMutation,
  useGetTranscriptQuery,
  useLazySendToEmailQuery,
} from "@/api";
import {
  AddNoteIcon,
  CopyDocumentIcon,
  DeleteDocumentIcon,
  EditDocumentIcon,
  MailIcon,
  SetingsIcon,
} from "@/components/icons";
import DefaultLayout from "@/layouts/default";

import {
  Accordion,
  AccordionItem,
  Alert,
  Avatar,
  Button,
  Card,
  CardBody,
  CardHeader,
  Divider,
  Drawer,
  DrawerBody,
  DrawerContent,
  DrawerFooter,
  DrawerHeader,
  Dropdown,
  DropdownItem,
  DropdownMenu,
  DropdownSection,
  DropdownTrigger,
  Input,
  Listbox,
  ListboxItem,
  Modal,
  ModalBody,
  ModalContent,
  ModalFooter,
  ModalHeader,
  ScrollShadow,
  Spacer,
  Spinner,
  useDisclosure,
  User,
} from "@heroui/react";
import clsx from "clsx";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const iconClasses =
  "text-xl text-default-500 pointer-events-none flex-shrink-0";
export default function Workspace() {
  const [edit] = useEditSpeeckerNamesMutation();
  const params = useParams();
  const {
    data: workSpaceData,
    isFetching,
    isLoading,
  } = useGetTranscriptQuery(
    { id: params.id! },
    { skip: typeof params.id !== "string" }
  );

  if (isLoading || isFetching)
    return (
      <DefaultLayout>
        <div className="flex justify-center items-center h-full">
          <Spinner />
        </div>
      </DefaultLayout>
    );
  if (!workSpaceData)
    return (
      <DefaultLayout>
        <div className="flex justify-center items-center p-10">
          <Alert color="danger">Нет данных</Alert>
        </div>
      </DefaultLayout>
    );

  const [mail, setMail] = useState("");
  const {
    isOpen: isOpenModal,
    onOpen: onOpenModal,
    onOpenChange: onOpenModalChange,
  } = useDisclosure();
  const [sendEmail] = useLazySendToEmailQuery();
  const { isOpen, onOpen, onOpenChange } = useDisclosure();
  const [workSpace] = useState(workSpaceData);
  const [editingId, setEditingId] = useState<number | null>(null);
  const [editName, setEditName] = useState("");
  const [updatedUsers, setUpdatedUsers] = useState<
    typeof workSpace.participants
  >([]);
  useEffect(() => {
    if (!isOpen) {
      setUpdatedUsers([]);
      setEditingId(null);
      setEditName("");
    }
  }, [isOpen]);

  // const params = useParams();

  const handleApiSaveChanges = (closeHandler: () => void) => {
    edit(updatedUsers);
    closeHandler();
  };

  const handleEditStart = (participant: (typeof workSpace.participants)[0]) => {
    setEditingId(participant.id);
    setEditName(participant.name);
  };

  const handleEditSave = () => {
    if (!editName.trim() || !editingId) return;
    setUpdatedUsers((prev) => {
      const index = prev.findIndex((u) => u.id === editingId);
      if (index !== -1) {
        const newUsers = [...prev];
        newUsers[index] = { ...newUsers[index], name: editName };
        return newUsers;
      } else {
        return [...prev, { id: editingId, name: editName }];
      }
    });
    setEditingId(null);
    setEditName("");
  };

  const sendEmailHanlder = async (onCloseModal: () => void) => {
    const html = document.createElement("html");
    const body = document.createElement("body");
    html.appendChild(body);
    body.innerHTML = JSON.stringify(workSpace.dialog);
    await sendEmail({
      email: mail,
      html,
    });
    onCloseModal();
  };

  return (
    <DefaultLayout>
      <Modal isOpen={isOpenModal} onOpenChange={onOpenModalChange}>
        <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader className="flex flex-col gap-1">
                Отправить расшифровку на почту
              </ModalHeader>
              <ModalBody>
                <Input
                  label="Почта"
                  placeholder="Укажите почту"
                  value={mail}
                  onChange={(e) => setMail(e.target.value)}
                  type="email"
                />
              </ModalBody>
              <ModalFooter>
                <Button color="danger" variant="light" onPress={onClose}>
                  Отмена
                </Button>
                <Button
                  color="primary"
                  onPress={() => sendEmailHanlder(onClose)}
                >
                  Отправить
                </Button>
              </ModalFooter>
            </>
          )}
        </ModalContent>
      </Modal>
      <div className="text-2xl font-semibold mb-10 flex justify-between p-2">
        <div>Расшифровки</div>
      </div>
      <Divider />
      <Spacer />
      <Card className="m-2" shadow="sm" key={"id"}>
        <CardHeader className="flex gap-3 px-5">
          <div className="flex justify-between w-full">
            <div className="flex flex-col">
              <p className="text-md">{workSpace.name}</p>
              <p className="text-small text-default-500">
                {new Date().toLocaleDateString()}{" "}
                {new Date().toLocaleTimeString()}
              </p>
            </div>
            <Dropdown>
              <DropdownTrigger>
                <Button isIconOnly variant="flat" size="sm">
                  <SetingsIcon />
                </Button>
              </DropdownTrigger>
              <DropdownMenu variant="faded">
                <DropdownSection showDivider title="Actions">
                  <DropdownItem
                    key="edit"
                    description="Позволяет редактировать участников всстречи"
                    shortcut="⌘⇧E"
                    startContent={<EditDocumentIcon className={iconClasses} />}
                    onPress={onOpen}
                  >
                    Редактировать участников
                  </DropdownItem>
                  <DropdownItem
                    key="SEND"
                    description="Отправить расшифровку на указанную почту"
                    shortcut="⌘⇧E"
                    onPress={onOpenModal}
                    startContent={<MailIcon className={iconClasses} />}
                  >
                    Отправить
                  </DropdownItem>
                  <DropdownItem
                    key="copy"
                    description="Скопирует ссылку на встречу"
                    shortcut="⌘C"
                    startContent={<CopyDocumentIcon className={iconClasses} />}
                  >
                    Копировать ссылку
                  </DropdownItem>
                </DropdownSection>
                <DropdownSection>
                  <DropdownItem
                    key="delete"
                    className="text-danger"
                    shortcut="⌘⇧D"
                    startContent={
                      <DeleteDocumentIcon
                        className={clsx(iconClasses, "text-danger")}
                      />
                    }
                  >
                    Удалить встречу
                  </DropdownItem>
                </DropdownSection>
              </DropdownMenu>
            </Dropdown>
          </div>
        </CardHeader>
        <Divider />
        <CardBody>
          <Accordion fullWidth>
            <AccordionItem
              key="1"
              subtitle="Нажмите чтобы открыть"
              title="Спикеры"
            >
              <Listbox>
                {workSpace.participants.map(({ id, name }) => (
                  <ListboxItem key={id}>
                    <User description="Product Designer" name={name} />
                  </ListboxItem>
                ))}
              </Listbox>
            </AccordionItem>
            <AccordionItem
              key="2"
              subtitle="Нажмите чтобы открыть"
              title="Выжимка встречи"
            >
              <ScrollShadow className="flex-1 p-4">
                <div className="flex flex-col gap-4 w-full">
                  {workSpace.summary}
                </div>
              </ScrollShadow>
            </AccordionItem>
            <AccordionItem
              key="3"
              subtitle="Нажмите чтобы открыть"
              title="Задачи"
            >
              <ScrollShadow className="flex-1 p-4">
                <div className="flex flex-col gap-4">
                  {workSpace.tasks.map((task) => (
                    <Card key={task.id} className="p-4">
                      <div className="flex flex-col gap-2">
                        <div className="flex items-start justify-between">
                          <h4 className="text-base font-medium">{task.name}</h4>
                        </div>
                        <Divider className="my-2" />
                        <div className="flex items-center justify-between">
                          <div className="flex items-center gap-2">
                            <Avatar
                              src={task.author.name}
                              name={task.author.name}
                              size="sm"
                            />
                            <span className="text-sm text-default-500">
                              {task.author.name}
                            </span>
                          </div>
                          <span className="text-xs text-default-400">
                            Due: {new Date(0).toLocaleDateString()}
                          </span>
                        </div>
                      </div>
                    </Card>
                  ))}
                </div>
              </ScrollShadow>
            </AccordionItem>
            <AccordionItem
              key="4"
              subtitle="Расшифровка встречи"
              title="Полная расшифрока"
            >
              <ScrollShadow className="flex-1 p-4">
                <div className="flex flex-col gap-4">
                  <ScrollShadow className="flex-1">
                    <div className="flex flex-col gap-4 w-full">
                      {workSpace.dialog.map(
                        ({ author, id, speech }, i, arr) => (
                          <div
                            key={id}
                            className={`flex gap-3 w-full flex-col items-start`}
                          >
                            <User
                              description="Product Designer"
                              name={author.name}
                            />
                            <div className={`flex flex-col max-w-full`}>
                              <div className={`rounded-lg flex flex-1 w-full`}>
                                <p className="text-sm w-full text-wrap">
                                  {speech}
                                </p>
                              </div>
                              <span className="mt-1 text-xs text-default-400">
                                {new Date(0).toLocaleTimeString()}
                              </span>
                            </div>
                            <Divider hidden={i === arr.length - 1} />
                          </div>
                        )
                      )}
                    </div>
                  </ScrollShadow>
                </div>
              </ScrollShadow>
            </AccordionItem>
          </Accordion>
        </CardBody>
      </Card>
      <br />
      <Divider />
      <br />
      <Drawer isOpen={isOpen} onOpenChange={onOpenChange}>
        <DrawerContent>
          {(onClose) => (
            <>
              <DrawerHeader className="flex flex-col gap-1">
                Участники встречи
              </DrawerHeader>
              <DrawerBody>
                <Listbox>
                  {workSpace.participants.map((participant) => {
                    const userFromInnerUpdated = updatedUsers.find(
                      (u) => u.id === participant.id
                    );

                    return (
                      <ListboxItem
                        key={participant.id}
                        className="flex items-center justify-between gap-2"
                        endContent={
                          editingId !== participant.id && (
                            <Button
                              isIconOnly
                              size="sm"
                              variant="light"
                              onPress={() => handleEditStart(participant)}
                            >
                              <EditDocumentIcon />
                            </Button>
                          )
                        }
                      >
                        {editingId === participant.id ? (
                          <Input
                            autoFocus
                            startContent={<User name={""} />}
                            value={editName}
                            onChange={(e) => setEditName(e.target.value)}
                            className="w-full"
                            endContent={
                              <Button
                                isIconOnly
                                size="sm"
                                color="primary"
                                variant="flat"
                                onPress={() => handleEditSave()}
                              >
                                <AddNoteIcon />
                              </Button>
                            }
                          />
                        ) : userFromInnerUpdated ? (
                          <User
                            name={userFromInnerUpdated.name}
                            description="Product Designer"
                          />
                        ) : (
                          <User
                            name={participant.name}
                            description="Product Designer"
                          />
                        )}
                      </ListboxItem>
                    );
                  })}
                </Listbox>
              </DrawerBody>
              <DrawerFooter>
                <Button color="danger" variant="light" onPress={onClose}>
                  Закрыть
                </Button>
                <Button
                  color="primary"
                  onPress={() => handleApiSaveChanges(onClose)}
                >
                  Сохранить изменения
                </Button>
              </DrawerFooter>
            </>
          )}
        </DrawerContent>
      </Drawer>
    </DefaultLayout>
  );
}
