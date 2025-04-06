import { useGetTranscriptsQuery } from "@/api";
import DefaultLayout from "@/layouts/default";

import {
  Card,
  CardHeader,
  CardBody,
  Image,
  Divider,
  Link,
  Accordion,
  AccordionItem,
  ScrollShadow,
  Spacer,
  Button,
  Spinner,
  Alert,
} from "@heroui/react";

export default function Workspaces() {
  const { data: workSpaces, isFetching, isLoading } = useGetTranscriptsQuery();

  if (isLoading || isFetching)
    return (
      <DefaultLayout>
        <div className="flex justify-center items-center h-full">
          <Spinner />
        </div>
      </DefaultLayout>
    );
  if (!workSpaces)
    return (
      <DefaultLayout>
        <div className="flex justify-center items-center p-10">
          <Alert color="danger">Нет данных</Alert>
        </div>
      </DefaultLayout>
    );
  return (
    <DefaultLayout>
      <div className="text-2xl font-semibold mb-10 flex justify-between p-2">
        <div>Расшифровки</div>
        <div>
          <Button size="sm">
            <Link
              isExternal
              className="flex items-center gap-1 text-current"
              href="/create"
              target="_self"
            >
              Добавить
            </Link>
          </Button>
        </div>
      </div>
      <Divider />
      <Spacer />
      {workSpaces.map(({ id, name, summary }) => (
        <Card className="m-2" shadow="sm" key={id}>
          <CardHeader className="flex gap-3">
            <Image
              alt="logo"
              height={40}
              radius="sm"
              src="https://loremflickr.com/40/40"
              width={40}
            />
            <div className="flex justify-between w-full">
              <div className="flex flex-col">
                <p className="text-md">{name}</p>
                <p className="text-small text-default-500">
                  {new Date().toLocaleDateString()}{" "}
                  {new Date().toLocaleTimeString()}
                </p>
              </div>
              <Link href={`workspace/${id}`} className="text-md">
                Подробнее
              </Link>
            </div>
          </CardHeader>
          <Divider />
          <CardBody>
            <Accordion fullWidth>
              <AccordionItem
                key="2"
                subtitle="Нажмите чтобы открыть"
                title="Выжимка встречи"
              >
                <ScrollShadow className="flex-1">
                  <div className="flex flex-col gap-4 w-full">{summary}</div>
                </ScrollShadow>
              </AccordionItem>
            </Accordion>
          </CardBody>
        </Card>
      ))}
    </DefaultLayout>
  );
}
